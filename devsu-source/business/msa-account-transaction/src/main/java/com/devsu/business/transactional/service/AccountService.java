package com.devsu.business.transactional.service;

import com.devsu.business.transactional.service.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto getById(Long accountId);
    List<AccountDto> getAll();
    AccountDto save(AccountDto accountDto);
    AccountDto update(AccountDto accountDto);
    boolean delete(Long accountId);
}
