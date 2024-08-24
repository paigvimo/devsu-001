package com.devsu.business.transactional.controller;

import com.devsu.business.transactional.service.AccountService;
import com.devsu.business.transactional.service.dto.AccountDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountController {
    AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable("id") Long accountId) {
        AccountDto accountDto = accountService.getById(accountId);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        List<AccountDto> accountDtoList = accountService.getAll();
        return ResponseEntity.ok(accountDtoList);
    }

    @PostMapping
    public ResponseEntity<AccountDto> save(@RequestBody AccountDto accountDto) {
        AccountDto accountDtoSaved = accountService.save(accountDto);
        return ResponseEntity.ok(accountDtoSaved);
    }

    @PutMapping
    public ResponseEntity<AccountDto> update(@RequestBody AccountDto accountDto) {
        AccountDto accountDtoSaved = accountService.update(accountDto);
        return ResponseEntity.ok(accountDtoSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long accountId) {
        boolean isDeleted = accountService.delete(accountId);
        return ResponseEntity.ok(isDeleted);
    }

}
