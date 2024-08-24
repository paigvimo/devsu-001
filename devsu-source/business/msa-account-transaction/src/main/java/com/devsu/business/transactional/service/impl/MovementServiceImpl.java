package com.devsu.business.transactional.service.impl;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.Transaction;
import com.devsu.business.transactional.domain.enums.TransactionType;
import com.devsu.business.transactional.exception.InsufficientFundsException;
import com.devsu.business.transactional.exception.NotFoundException;
import com.devsu.business.transactional.repository.TransactionRepository;
import com.devsu.business.transactional.service.MovementService;
import com.devsu.business.transactional.service.dto.AccountDto;
import com.devsu.business.transactional.service.dto.MovementDto;
import com.devsu.business.transactional.service.dto.TransactionDto;
import com.devsu.business.transactional.service.mapper.TransactionMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class MovementServiceImpl implements MovementService {
    TransactionRepository transactionRepository;

    @Override
    public Transaction performMovement(MovementDto movementDto, Account account) {
        Optional<Transaction> latestTransactionOpt = transactionRepository.findFirstByAccountOrderByTransactionDateDesc(account);
        if (latestTransactionOpt.isEmpty()) {
            throw new NotFoundException("CANNOT FOUND ANY PREVIOUS TRANSACTION FOR ACCOUNT {0}", account.getAccountId());
        }
        BigDecimal currentBalance = latestTransactionOpt.get().getBalance();
        BigDecimal inputAmount = movementDto.getAmount();
        BigDecimal nextBalance = currentBalance.add(inputAmount);

        if (nextBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException("INSUFFICIENT BALANCE FOR THIS TRANSACTION");
        }

        TransactionType transactionType = inputAmount.compareTo(BigDecimal.ZERO) < 0 ?
                TransactionType.DEB : TransactionType.CRE;

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(transactionType);
        transaction.setAmount(inputAmount);
        transaction.setBalance(nextBalance);
        return transaction;
    }

    @Override
    public Transaction performFirstMovement(Account account) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(TransactionType.CRE);
        transaction.setAmount(account.getInitialAmount());
        transaction.setBalance(account.getInitialAmount());
        return transaction;
    }
}
