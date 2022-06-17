package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.FindAccount;
import com.github.fabriciolfj.accountservice.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public record AccountFindByCpfCase(FindAccount findAccount) {

    public Mono<Account> execute(final Mono<String> monoCpf) {
        return monoCpf.flatMap(c -> {
            log.info("Find account: {}", c);
            return findAccount.findAccountByCPF(c);
        });
    }
}
