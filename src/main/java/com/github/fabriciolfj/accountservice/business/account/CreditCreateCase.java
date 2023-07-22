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

    public Mono<Extract> execute(final Mono<Extract> extract) {
        return extract
                .flatMap(e -> findExtract.findLast(e.getCodeConta()))
                .zipWith(extract)
                .map(v -> v.getT2().calculate(v.getT1()))
                .flatMap(newExtract -> saveExtract.save(newExtract));
    }
}
