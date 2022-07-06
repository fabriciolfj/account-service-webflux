package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.model.GetRateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateWithdrawListener {

    private final ObjectMapper objectMapper;

    public Consumer<String> updateWithdraw() {
        return value -> {
            log.info("Msg received reset rule rate: {}", value);
        };
    }

    private GetRateResponse toDto(final String msg) {
        try {
            return objectMapper.readValue(msg, GetRateResponse.class);
        } catch (Exception e) {
            log.error("Fail converter msg to dto, detailes: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
