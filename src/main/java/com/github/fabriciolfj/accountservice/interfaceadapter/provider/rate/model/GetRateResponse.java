package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetRateResponse {

    private BigDecimal rate;
}
