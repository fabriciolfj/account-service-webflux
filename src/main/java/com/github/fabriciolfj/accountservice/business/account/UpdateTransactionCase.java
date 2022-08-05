package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.UpdateTransaction;
import com.github.fabriciolfj.accountservice.domain.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateTransactionCase {

    private final UpdateTransaction provider;

    public Mono<Void> execute(final Transaction transaction) {
        return Mono.just(transaction)
                .doOnNext(t -> log.info("Transaction updated to autorize {}", t.getId()))
                .flatMap(t -> provider.processing(t));
    }
}
