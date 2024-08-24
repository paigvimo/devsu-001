package com.devsu.business.transactional.service.impl;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.enums.AccountStatus;
import com.devsu.business.transactional.exception.NotFoundException;
import com.devsu.business.transactional.repository.AccountRepository;
import com.devsu.business.transactional.service.AccountService;
import com.devsu.business.transactional.service.dto.AccountDto;
import com.devsu.business.transactional.service.mapper.AccountMapper;
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
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    AccountMapper accountMapper;

    @Override
    public AccountDto getById(Long accountNumber) {
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if (accountOptional.isPresent()) {
            return accountMapper.toDto(accountOptional.get());
        } else {
            throw new NotFoundException("ACCOUNT NUMBER {0} NOT FOUND", accountNumber);
        }
    }

    @Override
    public List<AccountDto> getAll() {
        List<Account> accountList = accountRepository.findByStatusNot(AccountStatus.DEL);
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : accountList) {
            accountDtoList.add(accountMapper.toDto(account));
        }
        return accountDtoList;
    }

    @Override
    @Transactional
    public AccountDto save(AccountDto accountDto) {
        Optional<Account> accountOptional = accountRepository.findById(accountDto.getAccountNumber());
        if (accountOptional.isPresent()) {
            throw new NotFoundException("ACCOUNT NUMBER {0} ALREADY EXIST", accountDto.getAccountNumber());
        } else {
            Account account = accountMapper.toEntity(accountDto);
            account = accountRepository.save(account);
            return accountMapper.toDto(account);
        }
    }

    @Override
    @Transactional
    public AccountDto update(AccountDto accountDto) {
        Optional<Account> accountOptional = accountRepository.findById(accountDto.getAccountNumber());
        if (accountOptional.isEmpty()) {
            throw new NotFoundException("ACCOUNT NUMBER {0} NOT EXIST", accountDto.getAccountNumber());
        } else {
            Account account = accountMapper.toEntity(accountDto);
            account = accountRepository.save(account);
            return accountMapper.toDto(account);
        }
    }

    @Override
    public boolean delete(Long accountNumber) {
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if (accountOptional.isEmpty()) {
            throw new NotFoundException ("ACCOUNT NUMBER {0} NOT EXIST", accountNumber);
        }
        Account account = accountOptional.get();
        account.setAccountStatus(AccountStatus.DEL);
        accountRepository.save(account);
        return true;
    }
}
