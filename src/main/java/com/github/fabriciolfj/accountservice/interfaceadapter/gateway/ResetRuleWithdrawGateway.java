package com.github.fabriciolfj.accountservice.interfaceadapter.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.RateProviderConverter;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.ResetRuleWithdrawProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResetRuleWithdrawGateway {

    private final ObjectMapper objectMapper;
    private final ResetRuleWithdrawProvider resetRuleWithdrawProvider;

    public void send(final Account account) {
        try {
            var findRule = RateProviderConverter.toRequest(account);
            resetRuleWithdrawProvider.send(objectMapper.writeValueAsString(findRule));
        } catch (Exception e) {
            log.error("Fail send reset rule withdraws. Details: {}", e.getMessage());
        }
    }
}
