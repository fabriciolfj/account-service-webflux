package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.domain.Extract;
import com.github.fabriciolfj.accountservice.domain.TypeOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DebitRateCreateCase {
    private final WithdrawCase withdrawCase;

    public Mono<Extract> execute(final String account, final BigDecimal value) {
        return withdrawCase.execute(account)
                .filter(result -> result.isNotWithdrawFree())
                .map(c -> {
                    var extract = Extract.newExtract(value.multiply(c.getRate().divide(BigDecimal.valueOf(100))), account, UUID.randomUUID().toString(), TypeOperation.DEBIT);
                    extract.setDescribe("Debit rate " + c.getRate());
                    return extract;
                });
    }

}
