package com.github.fabriciolfj.accountservice.business;

import com.github.fabriciolfj.accountservice.domain.Account;
import reactor.core.publisher.Mono;

public interface SaveAccount {

    Mono<Account> save(final Account account);
}
