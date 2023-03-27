package com.github.fabriciolfj.accountservice.interfaceadapter.gateway;

import com.github.fabriciolfj.accountservice.business.GetRate;
import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.RateProvider;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.RateProviderConverter;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.model.GetRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class  RateGateway implements GetRate {

    private RateProvider rateProvider;

    private RMapCacheReactive<Integer, GetRateResponse> rateCache;

    public RateGateway(final RateProvider rateProvider, final RedissonReactiveClient client) {
        rateCache = client.getMapCache("rate", new TypedJsonJacksonCodec(String.class, GetRateResponse.class));
        this.rateProvider = rateProvider;
    }

    @Override
    public Mono<Account> find(final Account account) {
        return rateCache.get(account.getScore())
                .doOnNext(value -> log.info("Rule rate found cache: {}", value))
                .switchIfEmpty(rateProvider.find(RateProviderConverter.toRequest(account)))
                .flatMap(value -> rateCache.fastPut(account.getScore(), value, 10, TimeUnit.MINUTES).thenReturn(value))
                .flatMap(resp -> {
                    account.setRate(resp.getRate());
                    account.setWithdraw(resp.getWithdraw());
                    return Mono.just(account.addRate(resp.getRate()));
                });
    }
}
