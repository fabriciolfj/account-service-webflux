package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.listener.model;

import com.github.fabriciolfj.accountservice.domain.ResetRuleAccount;

public class ResetRuleAccountConverter {

    private ResetRuleAccountConverter() {

    }

    public static ResetRuleAccount toDomain(final ReceiveRateResponse response) {
        return ResetRuleAccount.builder()
                .account(response.getAccount())
                .rate(response.getRate())
                .withdraw(response.getWithdraw())
                .build();
    }
}
