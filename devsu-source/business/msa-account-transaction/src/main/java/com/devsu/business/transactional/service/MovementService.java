package com.devsu.business.transactional.service;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.Transaction;
import com.devsu.business.transactional.service.dto.AccountDto;
import com.devsu.business.transactional.service.dto.MovementDto;

public interface MovementService {
    Transaction performMovement(MovementDto movementDto, Account account);
    Transaction performFirstMovement(Account accountDto);
}
