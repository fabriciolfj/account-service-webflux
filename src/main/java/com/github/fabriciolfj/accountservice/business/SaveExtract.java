package com.github.fabriciolfj.accountservice.business;

import com.github.fabriciolfj.accountservice.domain.Extract;
import reactor.core.publisher.Mono;

public interface SaveExtract {

    Mono<Extract> save(final Extract extract);
}
