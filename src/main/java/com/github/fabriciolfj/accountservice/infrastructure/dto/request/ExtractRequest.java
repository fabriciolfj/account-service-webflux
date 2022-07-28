package com.github.fabriciolfj.accountservice.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtractRequest {

    @JsonProperty("code_conta")
    @NotEmpty(message = "Codigo da conta nao informado")
    private String codeConta;
    private BigDecimal value;
    @NotNull(message = "${extract.transaction}")
    private String transaction;
}
