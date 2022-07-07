package com.github.fabriciolfj.accountservice.infrastructure.converter;

import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.infrastructure.dto.request.AccountRequest;
import com.github.fabriciolfj.accountservice.infrastructure.dto.response.AccountResponse;

import java.time.LocalDate;

public class AccountDtoConverter {

    private AccountDtoConverter() { }

    public static Account toDomain(final AccountRequest request) {
        final var account = Account
                .builder()
                .score(request.getScore())
                .birthday(LocalDate.parse(request.getBirthday()))
                .cpf(request.getCpf())
                .dateRegistration(LocalDate.now())
                .build();

        account.setBalanceInit(request.getValue());
        return account;
    }

    public static AccountResponse toResponse(final Account account) {
        return AccountResponse.builder()
                .code(account.getCode())
                .build();
    }
}
