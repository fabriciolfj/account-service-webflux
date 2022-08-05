package com.github.fabriciolfj.accountservice.interfaceadapter.repository.extract;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExtractRepository extends ReactiveCrudRepository<ExtractEntity, Long> {

    Mono<ExtractEntity> findFirstByContaOrderByDateExtratoDesc(final String conta);

    Mono<ExtractEntity> findFirstByContaOrderByDateExtratoAsc(final String conta);

    Flux<ExtractEntity> findByConta(final String account);

    @Modifying
    @Query("update extrato set status = :value where transaction = :id")
    Mono<Void> updateTransaction(@Param("value") final String status, @Param("id") final String id);
}
