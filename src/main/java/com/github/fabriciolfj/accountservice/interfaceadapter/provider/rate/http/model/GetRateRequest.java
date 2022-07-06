package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetRateRequest {

    private String birthday;
    private BigDecimal value;
    private Integer score;
}
