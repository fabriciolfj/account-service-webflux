package com.github.fabriciolfj.accountservice.business.account;

import com.github.fabriciolfj.accountservice.business.FindAccount;
import com.github.fabriciolfj.accountservice.business.UpdateAccount;
import com.github.fabriciolfj.accountservice.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class WithdrawCase {

    private final FindAccount findAccount;
    private final UpdateAccount updateAccount;

    private final ResetRuleWithdrawCase resetRuleWithdrawCase;

    public Mono<Account> execute(final String account) {
        return findAccount.findByCode(account)
                .map(c -> c.decrementWithdraw())
                .doOnNext(c -> log.info("Withdraw decremented: {}, to account: {}", c.getWithdraw(), c.getCode()))
                .flatMap(c -> updateAccount.execute(c))
                .filter(c -> c.isResetRule())
                .flatMap(c -> resetRuleWithdrawCase.execute(c));
    }

}
