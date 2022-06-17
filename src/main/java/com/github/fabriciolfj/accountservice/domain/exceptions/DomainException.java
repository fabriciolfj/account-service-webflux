package com.github.fabriciolfj.accountservice.domain.exceptions;

public class DomainException extends RuntimeException {

    public DomainException(final String msg) {
        super(msg);
    }
}
