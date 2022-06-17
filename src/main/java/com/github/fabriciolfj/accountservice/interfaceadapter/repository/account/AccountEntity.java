package com.github.fabriciolfj.accountservice.interfaceadapter.repository.account;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("conta")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountEntity {

    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String code;
    private String cpf;
    private String product;
    @Column("date_birthday")
    private LocalDate dateBirthday;
}
