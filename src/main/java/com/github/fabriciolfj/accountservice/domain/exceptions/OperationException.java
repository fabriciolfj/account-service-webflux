package com.github.fabriciolfj.accountservice.domain.exceptions;

public class OperationException extends RuntimeException {

    public OperationException(final String msg) {
        super(msg);
    }
}
