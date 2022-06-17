package com.github.fabriciolfj.accountservice.business;

import com.github.fabriciolfj.accountservice.domain.Account;
import reactor.core.publisher.Mono;

public interface FindAccount {
    Mono<Account> findAccountByCPF(final String cpf);
}
