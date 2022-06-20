package com.github.fabriciolfj.accountservice.interfaceadapter.gateway;

import com.github.fabriciolfj.accountservice.business.GetRate;
import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.RateProvider;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.RateProviderConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class RateGateway implements GetRate {

    private final RateProvider rateProvider;
    @Override
    public Mono<Account> find(final Account account) {
        return rateProvider
                .find(RateProviderConverter.toRequest(account))
                .flatMap(prod -> Mono.just(account.addRate(prod.getRate())))
                .log();
    }
}
