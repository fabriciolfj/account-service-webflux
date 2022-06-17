package com.github.fabriciolfj.accountservice.interfaceadapter.repository.extract;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExtractRepository extends ReactiveCrudRepository<ExtractEntity, Long> {

    Mono<ExtractEntity> findFirstByContaOrderByDateExtratoDesc(final String conta);

    Flux<ExtractEntity> findByConta(final String account);
}
