package com.github.fabriciolfj.accountservice.domain.exceptions;

public class AccountNotFoundException extends DomainException {

    public AccountNotFoundException(String msg) {
        super(msg);
    }
}
