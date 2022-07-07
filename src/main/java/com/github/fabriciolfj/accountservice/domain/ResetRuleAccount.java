package com.github.fabriciolfj.accountservice.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetRuleAccount {

    private String account;
    private BigDecimal rate;
    private int withdraw;
}
