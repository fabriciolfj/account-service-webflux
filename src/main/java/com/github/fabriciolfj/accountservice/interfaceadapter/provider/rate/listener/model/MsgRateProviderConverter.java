package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.listener.model;

import com.github.fabriciolfj.accountservice.domain.Account;

public class MsgRateProviderConverter {

    private MsgRateProviderConverter() { }

    public static final SendRateRequest toRequest(final Account account) {
        return SendRateRequest.builder()
                .birthday(account.getBirthday().toString())
                .score(account.getScore())
                .value(account.getBalanceInit())
                .account(account.getCode())
                .build();
    }
}
