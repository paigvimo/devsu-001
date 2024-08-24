package com.devsu.business.transactional.service.impl;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.Transaction;
import com.devsu.business.transactional.domain.enums.AccountStatus;
import com.devsu.business.transactional.exception.NotFoundException;
import com.devsu.business.transactional.repository.AccountRepository;
import com.devsu.business.transactional.repository.TransactionRepository;
import com.devsu.business.transactional.service.MovementService;
import com.devsu.business.transactional.service.TransactionService;
import com.devsu.business.transactional.service.dto.MovementDto;
import com.devsu.business.transactional.service.dto.TransactionDto;
import com.devsu.business.transactional.service.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;
    TransactionMapper transactionMapper;
    MovementService movementService;
    AccountRepository accountRepository;

    @Override
    public TransactionDto getById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            return transactionMapper.toDto(transactionOptional.get());
        } else {
            throw new NotFoundException("TRANSACTION WITH ID {0} NOT FOUND", id);
        }
    }

    @Override
    public List<TransactionDto> getAll() {
        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            transactionDtoList.add(transactionMapper.toDto(transaction));
        }
        return transactionDtoList;
    }

    @Override
    @Transactional
    public TransactionDto save(MovementDto movementDto) {
        Optional<Account> accountOptional = accountRepository.findById(movementDto.getAccountId());
        if (accountOptional.isEmpty()) {
            throw new NotFoundException("ACCOUNT NUMBER {0} NOT EXIST", movementDto.getAccountId());
        }
        Account account = accountOptional.get();
        if (!account.getAccountStatus().equals(AccountStatus.ACT)){
            throw new NotFoundException("ACCOUNT NUMBER {0} IS NOT {1}", movementDto.getAccountId(), AccountStatus.ACT.getDescription());
        }

        Transaction transaction = movementService.performMovement(movementDto, account);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }
}
