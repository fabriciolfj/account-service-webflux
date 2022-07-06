package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.listener.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SendRateRequest {

    private String birthday;
    private BigDecimal value;
    private Integer score;
    private String account;
}
