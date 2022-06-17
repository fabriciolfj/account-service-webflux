package com.github.fabriciolfj.accountservice.business;

import com.github.fabriciolfj.accountservice.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;

public interface ListAllAccounts {

    Mono<Page<Account>> findAll(final PageRequest  request);
}
