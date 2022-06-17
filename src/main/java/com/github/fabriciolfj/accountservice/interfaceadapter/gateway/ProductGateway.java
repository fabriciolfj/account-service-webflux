package com.github.fabriciolfj.accountservice.interfaceadapter.gateway;

import com.github.fabriciolfj.accountservice.business.LinkProduct;
import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.ProductProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductGateway implements LinkProduct {

    private final ProductProvider productProvider;
    @Override
    public Mono<Account> linkProduct(final Account account) {
        return productProvider
                .find(account.getBalanceInit(), account.getCode())
                .flatMap(prod -> Mono.just(account.addProduct(prod.getCode())))
                .log();
    }
}
