package com.github.fabriciolfj.accountservice.infrastructure.converter;

import com.github.fabriciolfj.accountservice.domain.Extract;
import com.github.fabriciolfj.accountservice.domain.TypeOperation;
import com.github.fabriciolfj.accountservice.infrastructure.dto.request.ExtractRequest;

public class ExtractDtoConverter {

    private ExtractDtoConverter() {

    }

    public static final Extract toDomainDebit(final ExtractRequest request) {
        return Extract.newExtract(request.getValue(), request.getCodeConta(), request.getTransaction(), TypeOperation.DEBIT);
    }

    public static final Extract toDomainCredit(final ExtractRequest request) {
        return Extract.newExtract(request.getValue(), request.getCodeConta(), request.getTransaction(), TypeOperation.CREDIT);
    }
}
