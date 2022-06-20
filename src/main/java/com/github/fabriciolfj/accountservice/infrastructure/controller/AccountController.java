package com.github.fabriciolfj.accountservice.infrastructure.controller;

import com.github.fabriciolfj.accountservice.business.account.AccountCreateCase;
import com.github.fabriciolfj.accountservice.infrastructure.converter.AccountDtoConverter;
import com.github.fabriciolfj.accountservice.infrastructure.dto.request.AccountRequest;
import com.github.fabriciolfj.accountservice.infrastructure.dto.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequestMapping("/api/v1/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountCreateCase createCase;

    @PostMapping
    public Mono<AccountResponse> create(@Valid @RequestBody final AccountRequest request) {
        return Mono.just(request)
                .map(AccountDtoConverter::toDomain)
                .flatMap(createCase::execute)
                .map(AccountDtoConverter::toResponse);
    }
}
