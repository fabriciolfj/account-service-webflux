package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.message.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceiveRateResponse {

    private BigDecimal rate;
    private int withdraw;
    private String account;
}
