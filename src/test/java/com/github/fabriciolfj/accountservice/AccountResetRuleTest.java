package com.github.fabriciolfj.accountservice;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AccountResetRuleTest {

    @Test
    void testAccountResetRule() {
        var now = LocalDate.now();
        var test = now.plusDays(29);

        System.out.println(now);
    }
}
