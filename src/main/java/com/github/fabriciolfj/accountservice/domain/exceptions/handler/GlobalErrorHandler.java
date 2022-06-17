package com.github.fabriciolfj.accountservice.domain.exceptions.handler;

import com.github.fabriciolfj.accountservice.domain.exceptions.DomainException;
import com.github.fabriciolfj.accountservice.domain.exceptions.OperationException;
import com.github.fabriciolfj.accountservice.domain.exceptions.handler.dto.Error;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Objects;

@Configuration
@Order(-2)
@ControllerAdvice
@Log4j2

public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    public static final String UTF_8 = "UTF-8";

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        log.error(throwable.getMessage(), throwable);

        var message = Objects.nonNull(throwable.getLocalizedMessage()) ? throwable.getLocalizedMessage() : "";
        var errorDetails = new Error(
                LocalDateTime.now(),
                message);

        var bufferFactory = serverWebExchange.getResponse().bufferFactory();
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        httpStatus = constraintViolation(throwable, errorDetails, httpStatus);
        httpStatus = domainBusinessException(throwable, errorDetails, httpStatus);
        httpStatus = infrastructureException(throwable, errorDetails, httpStatus);

        serverWebExchange.getResponse().setStatusCode(httpStatus);
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        String errorDetailsMessage = errorDetails.getMessage();
        if (Objects.isNull(errorDetailsMessage)) {
            errorDetailsMessage = "";
        }
        var dataBuffer = bufferFactory.wrap(errorDetailsMessage.getBytes(Charset.forName(UTF_8)));
        return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    protected HttpStatus domainBusinessException(Throwable throwable, Error errorDetails, HttpStatus httpStatus) {
        if (throwable instanceof DomainException) {
            errorDetails.setMessage(throwable.getMessage());
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (throwable instanceof ResourceAccessException) {
            errorDetails.setMessage("Não foi possível consumir o recurso remoto.");
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }
        return httpStatus;
    }

    protected HttpStatus infrastructureException(final
                                                 Throwable throwable,
                                                 final Error errorDetails,
                                                 final HttpStatus httpStatus) {
        if (throwable instanceof OperationException) {
            errorDetails.setMessage(throwable.getMessage());
        }
        return httpStatus;
    }

    protected HttpStatus constraintViolation(Throwable throwable, Error errorDetails, HttpStatus httpStatus) {
        if (throwable instanceof WebExchangeBindException) {
            var webExchangeBindException = (WebExchangeBindException) throwable;
            if (Objects.nonNull(webExchangeBindException.getAllErrors())) {
                StringBuilder stringBuilder = new StringBuilder();
                webExchangeBindException.getAllErrors().forEach(objectError -> {
                    stringBuilder.append(objectError.getDefaultMessage() + "\n");
                });
                errorDetails.setMessage(stringBuilder.toString());
            }
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }
        return httpStatus;
    }


}
