package com.github.fabriciolfj.accountservice.business;

import com.github.fabriciolfj.accountservice.domain.Account;
import reactor.core.publisher.Mono;

public interface LinkProduct {

    Mono<Account> linkProduct(final Account account);
}
