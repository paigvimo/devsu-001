package com.devsu.business.transactional.service;

import com.devsu.business.transactional.service.dto.MovementDto;
import com.devsu.business.transactional.service.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto getById(Long id);
    List<TransactionDto> getAll();
    TransactionDto save(MovementDto movementDto);
}
