package com.github.fabriciolfj.accountservice.domain;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    private String id;
    private StatusTransactions status;
}
