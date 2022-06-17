package com.github.fabriciolfj.accountservice.interfaceadapter.repository.account;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AccountRepository extends ReactiveSortingRepository<AccountEntity, Long> {

    Mono<AccountEntity> findByCode(final String code);

    Mono<AccountEntity> findByCpf(final String cpf);

    Flux<AccountEntity> findAll(Pageable pageable);
}
