package com.github.fabriciolfj.accountservice.business;


import com.github.fabriciolfj.accountservice.domain.Extract;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface FindExtract {

    Mono<Extract> findLast(final String account);

    Flux<Extract> findAll(final String account);
}
