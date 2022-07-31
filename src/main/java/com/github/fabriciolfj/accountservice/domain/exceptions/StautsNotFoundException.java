package com.github.fabriciolfj.accountservice.domain.exceptions;

public class StautsNotFoundException extends RuntimeException {

    public StautsNotFoundException(final String msg) {
        super(msg);
    }
}
