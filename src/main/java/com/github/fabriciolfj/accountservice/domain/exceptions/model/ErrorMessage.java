package com.github.fabriciolfj.accountservice.domain.exceptions.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorMessage {

    private String message;
    private String code;
    private List<ErrorDetails> details;
}
