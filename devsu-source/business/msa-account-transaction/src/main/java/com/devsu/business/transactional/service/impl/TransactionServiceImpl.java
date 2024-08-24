package com.devsu.business.transactional.service.impl;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.Transaction;
import com.devsu.business.transactional.exception.NotFoundException;
import com.devsu.business.transactional.repository.AccountRepository;
import com.devsu.business.transactional.repository.TransactionRepository;
import com.devsu.business.transactional.service.TransactionService;
import com.devsu.business.transactional.service.dto.TransactionDto;
import com.devsu.business.transactional.service.mapper.TransactionMapper;
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
    public TransactionDto save(TransactionDto transactionDto) {
        Optional<Account> accountOptional = accountRepository.findById(transactionDto.getAccount().getAccountNumber());
        if (accountOptional.isEmpty()) {
            throw new NotFoundException("ACCOUNT NUMBER {0} NOT EXIST", transactionDto.getAccount().getAccountNumber());
        }

        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }
}
