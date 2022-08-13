package com.github.fabriciolfj.accountservice.infrastructure.controller;

import com.github.fabriciolfj.accountservice.business.account.AccountCreateCase;
import com.github.fabriciolfj.accountservice.business.account.CreditCreateCase;
import com.github.fabriciolfj.accountservice.business.account.OperationDebitCase;
import com.github.fabriciolfj.accountservice.infrastructure.converter.AccountDtoConverter;
import com.github.fabriciolfj.accountservice.infrastructure.converter.ExtractDtoConverter;
import com.github.fabriciolfj.accountservice.infrastructure.dto.request.AccountRequest;
import com.github.fabriciolfj.accountservice.infrastructure.dto.request.ExtractRequest;
import com.github.fabriciolfj.accountservice.infrastructure.dto.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequestMapping("/api/v1/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountCreateCase createCase;
    private final OperationDebitCase operationDebitCase;

    private final CreditCreateCase creditCreateCase;

    @PostMapping
    public Mono<AccountResponse> create(@Valid @RequestBody final AccountRequest request) {
        return Mono.just(request)
                .map(AccountDtoConverter::toDomain)
                .flatMap(createCase::execute)
                .map(AccountDtoConverter::toResponse);
    }

    @PutMapping("/credits")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createCredit(@Valid @RequestBody final ExtractRequest request) {
        return Mono.just(request)
                .map(ExtractDtoConverter::toDomainCredit)
                .flatMap(creditCreateCase::execute)
                .then();
    }

    @PutMapping("/debits")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createDebit(@Valid @RequestBody final ExtractRequest request) {
        return Mono.just(request)
                .map(ExtractDtoConverter::toDomainDebit)
                .flatMap(operationDebitCase::execute)
                .then();
    }
}
