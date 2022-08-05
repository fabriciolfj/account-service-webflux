package com.github.fabriciolfj.accountservice.domain;

import com.github.fabriciolfj.accountservice.domain.exceptions.StautsNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusTransactions {
    APPROVED("approved"), REFUSED("refuse"), PENDING("pending");

    private String describe;

    public static StatusTransactions toEnum(final String describe) {
        return Arrays.stream(StatusTransactions.values())
                .filter(value -> value.describe.equals(describe))
                .findFirst()
                .orElseThrow(() -> new StautsNotFoundException("Status not found: " + describe));
    }
}
