package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate;

import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.model.GetRateRequest;

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
