package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.ListAllAccounts;
import com.github.fabriciolfj.accountservice.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public record AccountFindAllCase(ListAllAccounts allAccounts) {

    public Mono<Page<Account>> execute(final PageRequest request) {
        log.info("Find all accounts : {}", request);
        return allAccounts.findAll(request);
    }
}
