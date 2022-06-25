package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.FindAccount;
import com.github.fabriciolfj.accountservice.domain.Extract;
import com.github.fabriciolfj.accountservice.domain.TypeOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DebitRateCreateCase {

    private final FindAccount findAccount;

    public Mono<Extract> execute(final String account, final BigDecimal value) {
        return findAccount
                .findByCode(account)
                .map(c -> {
                    var extract = Extract.newExtract(value.multiply(c.getRate().divide(BigDecimal.valueOf(100))), account, TypeOperation.DEBIT);
                    extract.setDescribe("Debit rate " + c.getRate());
                    return extract;
                })
                .log();
    }
}
