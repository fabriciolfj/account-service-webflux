package com.github.fabriciolfj.accountservice.business;

import com.github.fabriciolfj.accountservice.domain.Transaction;
import reactor.core.publisher.Mono;

public interface UpdateTransaction {

    Mono<Void> processing(final Transaction transaction);
}
