package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.accountservice.business.account.UpdateResetRuleCase;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.message.model.ReceiveRateResponse;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.message.converter.ResetRuleAccountConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class UpdateWithdrawListener {

    private final ObjectMapper objectMapper;
    private final UpdateResetRuleCase updateResetRuleCase;

    @Bean
    public Consumer<String> updateWithdraw() {
        return value -> {
            log.info("Msg received reset rule rate: {}", value);
            var receive = toDto(value);
            updateResetRuleCase.execute(ResetRuleAccountConverter.toDomain(receive))
                    .subscribe();
        };
    }

    private ReceiveRateResponse toDto(final String msg) {
        try {
            return objectMapper.readValue(msg, ReceiveRateResponse.class);
        } catch (Exception e) {
            log.error("Fail converter msg to dto, detailes: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
