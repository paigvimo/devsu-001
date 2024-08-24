package com.devsu.business.transactional.service;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.Transaction;
import com.devsu.business.transactional.domain.enums.TransactionType;
import com.devsu.business.transactional.mocks.AccountMock;
import com.devsu.business.transactional.repository.TransactionRepository;
import com.devsu.business.transactional.service.impl.MovementServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class MovementServiceImplIntegrationTest {

    @Autowired
    private MovementServiceImpl movementService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void givenAccount_whenPerformFirstMovement_thenFirstTransactionIsCreated() {
        Account account = AccountMock.mockAccountEntity();

        Transaction result = movementService.performFirstMovement(account);

        assertThat(result).isNotNull();
        assertThat(result.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(500));
        assertThat(result.getBalance()).isEqualByComparingTo(BigDecimal.valueOf(500));
        assertThat(result.getTransactionType()).isEqualTo(TransactionType.CRE);
    }
}
