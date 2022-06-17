package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.LinkProduct;
import com.github.fabriciolfj.accountservice.business.SaveAccount;
import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.domain.Extract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountCase {

    private final SaveAccount saveAccount;
    private final LinkProduct linkProduct;

    public Mono<Account> execute(final Mono<Account> account) {
        return account
                .map(Account::genereteCode)
                .map(c -> {
                    var extract = Extract.initial(c.getBalanceInit(), c.getCode());
                    return c.addExtrato(extract);
                })
                .flatMap(linkProduct::linkProduct)
                .flatMap(c -> saveAccount.save(c));
    }
}
