package com.github.fabriciolfj.accountservice.interfaceadapter.repository.extract;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "extrato")
@Builder
@Data
public class ExtractEntity {

    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String conta;
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal balance;
    @Column("describe_registry")
    private String describe;
    @Column("date_extrato")
    private LocalDateTime dateExtrato;
}
