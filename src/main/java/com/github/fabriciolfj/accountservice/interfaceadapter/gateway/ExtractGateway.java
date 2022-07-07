package com.github.fabriciolfj.accountservice.interfaceadapter.gateway;

import com.github.fabriciolfj.accountservice.business.FindExtract;
import com.github.fabriciolfj.accountservice.business.SaveExtract;
import com.github.fabriciolfj.accountservice.domain.Extract;
import com.github.fabriciolfj.accountservice.domain.exceptions.ExtractProcessException;
import com.github.fabriciolfj.accountservice.interfaceadapter.repository.extract.ExtractEntity;
import com.github.fabriciolfj.accountservice.interfaceadapter.repository.extract.ExtractRepository;
import com.github.fabriciolfj.accountservice.interfaceadapter.repository.mapper.ExtractEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ExtractGateway  implements FindExtract, SaveExtract {

    private final ExtractRepository extractRepository;

    public Mono<ExtractEntity> save(final ExtractEntity extract) {
        return extractRepository.save(extract);
    }

    @Override
    public Mono<Extract> findLast(final String account) {
        return Mono.just(account)
                .flatMap(c -> extractRepository.findFirstByContaOrderByDateExtratoDesc(c))
                .map(ExtractEntityMapper::toDomain)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ExtractProcessException("Extract not found to account: " + account))));
    }

    public Mono<Extract> findFirst(final String account) {
        return Mono.just(account)
                .flatMap(c -> extractRepository.findFirstByContaOrderByDateExtratoAsc(c))
                .map(ExtractEntityMapper::toDomain)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ExtractProcessException("Extract not found to account: " + account))));
    }

    @Override
    public Flux<Extract> findAll(final String account) {
        return extractRepository.findByConta(account)
                .map(ExtractEntityMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Extract> save(final Extract extract) {
        return Mono.just(extract)
                .flatMap(e -> extractRepository.save(ExtractEntityMapper.toEntity(e)))
                .thenReturn(extract);
    }
}
