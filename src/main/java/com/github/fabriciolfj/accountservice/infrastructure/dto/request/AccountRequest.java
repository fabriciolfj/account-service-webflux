package com.github.fabriciolfj.accountservice.infrastructure.dto.request;

import com.github.fabriciolfj.accountservice.domain.exceptions.annotations.ValidateFormatDate;
import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountRequest {

    private String cpf;
    private Integer score;
    private BigDecimal value;
    @ValidateFormatDate(message = "{account.date}")
    private String birthday;
}
