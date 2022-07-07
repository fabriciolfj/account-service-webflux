package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.FindAccount;
import com.github.fabriciolfj.accountservice.business.UpdateAccount;
import com.github.fabriciolfj.accountservice.domain.ResetRuleAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateResetRuleCase {

    private final UpdateAccount updateAccount;
    private final FindAccount findAccount;

    public Mono<Void> execute(final ResetRuleAccount reset) {
        return findAccount.findByCode(reset.getAccount())
                .map(c -> {
                    c.setWithdraw(reset.getWithdraw());
                    c.setRate(reset.getRate());
                    c.setDateRegistration(LocalDate.now());
                    return c;
                })
                .doOnNext(c -> log.info("Update account: {}", c))
                .flatMap(c -> updateAccount.execute(c))
                .then();
    }
}
