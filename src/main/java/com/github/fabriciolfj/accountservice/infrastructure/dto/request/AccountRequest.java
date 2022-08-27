package com.github.fabriciolfj.accountservice.infrastructure.dto.request;

import com.github.fabriciolfj.accountservice.domain.exceptions.annotations.ValidateFormatDate;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountRequest {

    @NotEmpty(message = "cpf not found")
    private String cpf;
    @NotNull(message = "score not found")
    private Integer score;
    @NotNull(message = "value not found")
    private BigDecimal value;
    @ValidateFormatDate(message = "{account.date}")
    private String birthday;
}
