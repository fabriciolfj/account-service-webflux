package com.github.fabriciolfj.accountservice.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtractRequest {

    @JsonProperty("code_conta")
    private String codeConta;
    private BigDecimal value;
}
