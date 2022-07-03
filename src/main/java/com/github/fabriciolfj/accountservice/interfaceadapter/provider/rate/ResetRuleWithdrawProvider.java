package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResetRuleWithdrawProvider {

    @Autowired
    private StreamBridge streamBridge;

    @Value("${topic.reset-withdraw:reset-withdraw}")
    private String topic;

    public void send(final String value) {
        streamBridge.send(topic, value);
    }
}
