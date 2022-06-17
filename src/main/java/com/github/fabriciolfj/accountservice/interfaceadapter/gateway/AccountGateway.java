package com.github.fabriciolfj.accountservice.interfaceadapter.gateway;

import com.github.fabriciolfj.accountservice.business.FindAccount;
import com.github.fabriciolfj.accountservice.business.ListAllAccounts;
import com.github.fabriciolfj.accountservice.business.SaveAccount;
import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.domain.exceptions.AccountNotFoundException;
import com.github.fabriciolfj.accountservice.interfaceadapter.repository.account.AccountRepository;
import com.github.fabriciolfj.accountservice.interfaceadapter.repository.mapper.AccountEntityMapper;
import com.github.fabriciolfj.accountservice.interfaceadapter.repository.mapper.ExtractEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountGateway implements SaveAccount, FindAccount, ListAllAccounts {

    private final AccountRepository accountRepository;
    private final ExtractGateway extractGateway;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Account> save(final Account account) {
        return Mono.just(account)
                .map(AccountEntityMapper::toEntity)
                .flatMap(accountRepository::save)
                .map(c -> ExtractEntityMapper.toEntity(account.findFirst()))
                .flatMap(extractEntity -> extractGateway.save(extractEntity))
                .flatMap(result -> {
                    log.info("Save extract to account: {}-{}", result.getId(), account.getCode());
                    return Mono.just(account);
                });
    }
    @Override
    @Transactional(propagation = Propagation.NEVER, readOnly = true)
    public Mono<Account> findAccountByCPF(final String cpf) {
        return accountRepository.findByCpf(cpf)
                .zipWhen(ac -> extractGateway.findLast(ac.getCode()))
                .map(t -> AccountEntityMapper.toDomain(t.getT1(), t.getT2()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new AccountNotFoundException("Account not found to cpf " + cpf))))
                .log();
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, readOnly = true)
    public Mono<Page<Account>> findAll(final PageRequest request) {
        return accountRepository.findAll(request)
                .map(AccountEntityMapper::toDomain)
                .collectList()
                .zipWhen(ac -> accountRepository.count())
                .map(t -> new PageImpl<>(t.getT1(), request, t.getT2()));
    }
}
