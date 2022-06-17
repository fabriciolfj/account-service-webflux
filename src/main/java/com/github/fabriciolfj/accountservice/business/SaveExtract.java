package com.github.fabriciolfj.accountservice.business;

import com.github.fabriciolfj.accountservice.domain.Extract;
import reactor.core.publisher.Mono;

public interface SaveExtract {

    Mono<Void> save(final Mono<Extract> extract);
}
