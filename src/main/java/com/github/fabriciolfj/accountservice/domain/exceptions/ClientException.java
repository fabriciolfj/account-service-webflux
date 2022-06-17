package com.github.fabriciolfj.accountservice.domain.exceptions;

public class ClientException extends RuntimeException {

    public ClientException(final String msg) {
        super(msg);
    }
}
