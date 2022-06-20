package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.GetRate;
import com.github.fabriciolfj.accountservice.business.SaveAccount;
import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.domain.Extract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountCreateCase {

    private final SaveAccount saveAccount;
    private final GetRate getRate;

    public Mono<Account> execute(final Account account) {
        return Mono.just(account)
                .map(Account::genereteCode)
                .map(c -> {
                    var extract = Extract.initial(c.getBalanceInit(), c.getCode());
                    return c.addExtrato(extract);
                })
                .flatMap(getRate::find)
                .flatMap(c -> saveAccount.save(c));
    }
}
