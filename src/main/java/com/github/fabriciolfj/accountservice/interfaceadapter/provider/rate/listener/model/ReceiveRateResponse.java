package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.listener.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceiveRateResponse {

    private BigDecimal rate;
    private String account;
}
