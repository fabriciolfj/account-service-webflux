package com.github.fabriciolfj.accountservice.interfaceadapter.repository.mapper;

import com.github.fabriciolfj.accountservice.domain.Extract;
import com.github.fabriciolfj.accountservice.interfaceadapter.repository.extract.ExtractEntity;

public class ExtractEntityMapper {

    private ExtractEntityMapper() {

    }

    public static final Extract toDomain(final ExtractEntity entity) {
        return Extract
                .builder()
                .codeConta(entity.getConta())
                .date(entity.getDateExtrato())
                .balance(entity.getBalance())
                .credit(entity.getCredit())
                .debit(entity.getDebit())
                .describe(entity.getDescribe())
                .build();
    }

    public static final ExtractEntity toEntity(final Extract extract) {
        return ExtractEntity.builder()
                .conta(extract.getCodeConta())
                .balance(extract.getBalance())
                .credit(extract.getCredit())
                .dateExtrato(extract.getDate())
                .debit(extract.getDebit())
                .describe(extract.getDescribe())
                .build();
    }
}
