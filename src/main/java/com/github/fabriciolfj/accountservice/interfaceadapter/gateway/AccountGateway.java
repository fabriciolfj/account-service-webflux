package com.github.fabriciolfj.accountservice.interfaceadapter.gateway;

import com.github.fabriciolfj.accountservice.business.FindAccount;
import com.github.fabriciolfj.accountservice.business.ListAllAccounts;
import com.github.fabriciolfj.accountservice.business.SaveAccount;
import com.github.fabriciolfj.accountservice.business.UpdateAccount;
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
public class AccountGateway implements SaveAccount, FindAccount, ListAllAccounts, UpdateAccount {

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
    public Mono<Account> findByCode(final String code) {
        return accountRepository.findByCode(code)
                .map(AccountEntityMapper::toDomain)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new AccountNotFoundException("Account not found to code " + code))));
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, readOnly = true)
    public Mono<Page<Account>> findAll(final PageRequest request) {
        return accountRepository.findAllBy(request)
                .map(AccountEntityMapper::toDomain)
                .collectList()
                .zipWhen(ac -> accountRepository.count())
                .map(t -> new PageImpl<>(t.getT1(), request, t.getT2()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Account> execute(final Account account) {
        return Mono.just(account)
                .flatMap(e -> accountRepository.findByCode(e.getCode()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new AccountNotFoundException("Account not found to code " + account.getCode()))))
                .doOnNext(result -> log.info("Found account: {}", result.getId()))
                .map(entity -> AccountEntityMapper.toMerge(account, entity))
                .doOnNext(result -> log.info("Update account: {}", result))
                .flatMap(accountRepository::save)
                .thenReturn(account);
    }
}
