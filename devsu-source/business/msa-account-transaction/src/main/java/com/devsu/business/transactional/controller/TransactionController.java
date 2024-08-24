package com.devsu.business.transactional.controller;

import com.devsu.business.transactional.service.TransactionService;
import com.devsu.business.transactional.service.dto.MovementDto;
import com.devsu.business.transactional.service.dto.TransactionDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TransactionController {

    TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable("id") Long transactionId) {
        TransactionDto transactionDto = transactionService.getById(transactionId);
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAll() {
        List<TransactionDto> transactionDtoList = transactionService.getAll();
        return ResponseEntity.ok(transactionDtoList);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> save(@RequestBody MovementDto movementDto) {
        TransactionDto transactionDtoSaved = transactionService.save(movementDto);
        return ResponseEntity.ok(transactionDtoSaved);
    }
}
