package com.github.fabriciolfj.accountservice.interfaceadapter.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.accountservice.domain.Account;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.listener.ResetRuleWithdrawProvider;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.listener.model.MsgRateProviderConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResetRuleWithdrawGateway {

    private final ObjectMapper objectMapper;
    private final ResetRuleWithdrawProvider resetRuleWithdrawProvider;

    public void send(final Account account) {
        try {
            var findRule = MsgRateProviderConverter.toRequest(account);
            resetRuleWithdrawProvider.send(objectMapper.writeValueAsString(findRule));
        } catch (Exception e) {
            log.error("Fail send reset rule withdraws. Details: {}", e.getMessage());
        }
    }
}
