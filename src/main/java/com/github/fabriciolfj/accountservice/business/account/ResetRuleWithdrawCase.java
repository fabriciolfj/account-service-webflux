package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.domain.exceptions.ResetRuleWithdrawException;
import com.github.fabriciolfj.accountservice.interfaceadapter.gateway.ResetRuleWithdrawGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResetRuleWithdrawCase {

    private final ResetRuleWithdrawGateway gateway;

    public Mono<Account> execute(final Account account) {
        return Mono.just(account)
                .doOnNext(s -> log.info("Send event reset: {}", account))
                .map(c -> {
                    gateway.send(c);
                    return c;
                }).onErrorMap(e -> new ResetRuleWithdrawException("Rule reset fail, details: " + e.getMessage()));
    }
}
