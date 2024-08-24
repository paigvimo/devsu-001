package com.devsu.business.transactional.mocks;

import static org.mockito.Mockito.*;
import java.math.BigDecimal;

import com.devsu.business.transactional.domain.Account;
import org.checkerframework.checker.units.qual.A;

public class AccountMock {

    public static Account mockAccountEntity() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setInitialAmount(BigDecimal.valueOf(500));

        return account;
    }

}
