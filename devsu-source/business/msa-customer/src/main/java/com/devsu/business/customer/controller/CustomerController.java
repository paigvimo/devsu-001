package com.devsu.business.customer.controller;

import com.devsu.business.customer.service.CustomerService;
import com.devsu.business.customer.service.dto.CustomerDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerController {
    CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable long id) {
        CustomerDto customerDto = customerService.getById(id);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/identificationNumber/{identificationNumber}")
    public ResponseEntity<CustomerDto> getByIdentificationNumber(@PathVariable String identificationNumber) {
        CustomerDto customerDto = customerService.getByIdentificationNumber(identificationNumber);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<CustomerDto> customerDtoList = customerService.getAll();
        return ResponseEntity.ok(customerDtoList);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        CustomerDto customerDtoSaved = customerService.save(customerDto);
        return ResponseEntity.ok(customerDtoSaved);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto) {
        CustomerDto customerDtoSaved = customerService.update(customerDto);
        return ResponseEntity.ok(customerDtoSaved);
    }

    @DeleteMapping("/{identificationNumber}")
    public ResponseEntity<Boolean> delete(@PathVariable String identificationNumber) {
        boolean isDeleted = customerService.delete(identificationNumber);
        return ResponseEntity.ok(isDeleted);
    }
}
