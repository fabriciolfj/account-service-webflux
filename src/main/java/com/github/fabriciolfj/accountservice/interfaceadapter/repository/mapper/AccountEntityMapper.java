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
                .rate(entity.getRate())
                .cpf(entity.getCpf())
                .birthday(entity.getDateBirthday())
                .score(entity.getScore())
                .extracts(List.of(extract))
                .build();
    }

    public static Account toDomain(final AccountEntity entity) {
        return Account.builder()
                .balanceInit(BigDecimal.ZERO)
                .rate(entity.getRate())
                .cpf(entity.getCpf())
                .birthday(entity.getDateBirthday())
                .score(entity.getScore())
                .extracts(Collections.emptyList())
                .build();
    }

    public static final AccountEntity toEntity(final Account account) {
        return AccountEntity.builder()
                .code(account.getCode())
                .score(account.getScore())
                .dateBirthday(account.getBirthday())
                .cpf(account.getCpf())
                .rate(account.getRate())
                .build();
    }
}
