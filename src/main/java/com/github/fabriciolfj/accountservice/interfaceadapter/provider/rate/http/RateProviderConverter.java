package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http;

import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.model.GetRateRequest;

public class RateProviderConverter {

    private RateProviderConverter() { }

    public static final GetRateRequest toRequest(final Account account) {
        return GetRateRequest.builder()
                .birthday(account.getBirthday().toString())
                .score(account.getScore())
                .value(account.getBalanceInit())
                .build();
    }
}
