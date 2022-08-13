package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.FindExtract;
import com.github.fabriciolfj.accountservice.business.SaveExtract;
import com.github.fabriciolfj.accountservice.domain.Extract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditCreateCase {

    private final FindExtract findExtract;
    private final SaveExtract saveExtract;

    public Mono<Extract> execute(final Extract extract) {
        return Mono.just(extract)
                .flatMap(e -> findExtract.findLast(e.getCodeConta()))
                .map(e -> extract.calculate(e))
                .flatMap(newExtract -> saveExtract.save(newExtract));
    }
}
