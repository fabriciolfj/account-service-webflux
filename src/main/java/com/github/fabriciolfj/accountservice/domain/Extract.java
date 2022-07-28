package com.github.fabriciolfj.accountservice.domain;

import com.github.fabriciolfj.accountservice.domain.exceptions.DomainException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Extract {

    private static final int EQUALS = 0;
    private static final int DIF = 1;
    private static final String CREDIT = "Credit";
    private static final String DEBIT = "Debit";

    private String codeConta;
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal balance;
    private String describe;
    private LocalDateTime date;

    private String transaction;
    public static Extract initial(final BigDecimal balance, final String code) {
        var extract = extractDefault(code);
        extract.setBalance(balance);
        extract.setCredit(balance);
        extract.setDebit(BigDecimal.ZERO);
        extract.setCodeConta(code);
        extract.setDescribe(CREDIT + " value:" + balance);
        extract.setTransaction(UUID.randomUUID().toString());
        return extract;
    }

    public static Extract newExtract(final BigDecimal value, final String code, final String transaction, final TypeOperation operation) {
        var extract = extractDefault(code);
        switch (operation) {
            case CREDIT -> {
                extract.setCredit(value);
                extract.setDebit(BigDecimal.ZERO);
                extract.setDescribe(CREDIT + " value:" + value);
                extract.setTransaction(transaction);
            }
            case DEBIT -> {
                extract.setDebit(value);
                extract.setCredit(BigDecimal.ZERO);
                extract.setDescribe(DEBIT + " value:" + value);
                extract.setTransaction(transaction);
            }
        }

        return extract;
    }

    public Extract calculate(final Extract last) {
        var value = BigDecimal.ZERO;
        switch (debit.compareTo(BigDecimal.ZERO)) {
            case EQUALS -> {
                value = last.balance.add(credit);
                balance = value;
            }
            case DIF -> {
                value = last.balance.subtract(debit);
                balance = value;
            }
            default -> throw new DomainException("Operation calculate extract fail");
        }

        return this;
    }

    private static Extract extractDefault(final String code) {
        return Extract
                .builder()
                .codeConta(code)
                .date(LocalDateTime.now())
                .build();
    }
}
