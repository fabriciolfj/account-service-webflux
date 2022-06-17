package com.github.fabriciolfj.accountservice.domain;

import com.github.fabriciolfj.accountservice.domain.exceptions.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.stream.Stream.of;

@Getter
@AllArgsConstructor
public enum TypeOperation {

    DEBIT("debit"), CREDIT("credit");

    private String operation;

    public static TypeOperation toEnum(final String describe) {
        return of(TypeOperation.values())
                .filter(op -> op.getOperation().equalsIgnoreCase(describe))
                .findFirst()
                .orElseThrow(() -> new DomainException("Operation not found, to describe: " + describe));
    }


}
