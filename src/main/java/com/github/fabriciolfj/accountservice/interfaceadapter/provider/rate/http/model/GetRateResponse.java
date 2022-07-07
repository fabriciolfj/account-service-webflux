package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetRateResponse {

    private BigDecimal rate;
    private Integer withdraw;
}
