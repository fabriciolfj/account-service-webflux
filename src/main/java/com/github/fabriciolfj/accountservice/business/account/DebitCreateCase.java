package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.FindExtract;
import com.github.fabriciolfj.accountservice.business.SaveExtract;
import com.github.fabriciolfj.accountservice.domain.Extract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class DebitCreateCase {
    private final FindExtract findExtract;
    private final SaveExtract saveExtract;

    public Mono<Extract> execute(final Extract extract) {
        return Mono.just(extract)
                .doOnNext(e -> log.info("Account extract debit: {}", e.getCodeConta()))
                .flatMap(ex ->
                        findExtract.findLast(ex.getCodeConta())
                            .map(result -> ex.calculate(result)))
                .flatMap(saveExtract::save)
                .doOnNext(r -> log.info("Extract save: {}", r))
                .flatMap(result -> Mono.just(extract));
    }
}
