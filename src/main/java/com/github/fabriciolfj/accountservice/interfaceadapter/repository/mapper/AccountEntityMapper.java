package com.github.fabriciolfj.accountservice.interfaceadapter.repository.mapper;

import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.domain.Extract;
import com.github.fabriciolfj.accountservice.interfaceadapter.repository.account.AccountEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class AccountEntityMapper {

    private AccountEntityMapper() {

    }

    public static Account toDomain(final AccountEntity entity, final Extract extract) {
        return Account.builder()
                .balanceInit(extract.getBalance())
                .product(entity.getProduct())
                .cpf(entity.getCpf())
                .extracts(List.of(extract))
                .build();
    }

    public static Account toDomain(final AccountEntity entity) {
        return Account.builder()
                .balanceInit(BigDecimal.ZERO)
                .product(entity.getProduct())
                .cpf(entity.getCpf())
                .extracts(Collections.emptyList())
                .build();
    }

    public static final AccountEntity toEntity(final Account account) {
        return AccountEntity.builder()
                .code(account.getCode())
                .cpf(account.getCpf())
                .product(account.getProduct())
                .build();
    }
}
