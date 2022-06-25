package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.domain.Extract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class OperationDebitCase {

    private final DebitCreateCase debitCreateCase;
    private final DebitRateCreateCase debitRateCreateCase;

    public Mono<Extract> execute(final Extract extract) {
        return Mono.just(extract)
                .flatMap(debitCreateCase::execute)
                .doOnNext(c -> log.info("Extract saved : {}", c))
                .flatMap(c -> debitRateCreateCase.execute(extract.getCodeConta(), extract.getDebit()))
                .flatMap(debitCreateCase::execute);
    }
}
