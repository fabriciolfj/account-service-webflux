package com.github.fabriciolfj.accountservice.interfaceadapter.provider.transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.accountservice.business.account.UpdateTransactionCase;
import com.github.fabriciolfj.accountservice.domain.StatusTransactions;
import com.github.fabriciolfj.accountservice.domain.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateTransactionsAutorizeProvider {

    private final UpdateTransactionCase useCase;
    private final ObjectMapper objectMapper;

    @Bean
    public Consumer<String> transactionAutorize() {
        return value -> {
            var dto = toDto(value);
            useCase.execute(Transaction.builder()
                            .id(dto.getTransaction())
                    .status(StatusTransactions.APPROVED).build())
                    .subscribe(
                            e -> log.info("Consumer success"),
                            err -> log.error("Fail consumer"),
                            () -> log.info("Complete consumer update transaction approve")
                    );
        };
    }

    private TransactionsDTO toDto(final String msg) {
        try {
            return objectMapper.readValue(msg, TransactionsDTO.class);
        } catch (Exception e) {
            log.error("Fail converter msg to dto, detailes: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
