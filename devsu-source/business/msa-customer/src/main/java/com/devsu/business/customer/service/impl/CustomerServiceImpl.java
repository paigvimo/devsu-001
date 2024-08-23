package com.devsu.business.customer.service.impl;

import com.devsu.business.customer.domain.Customer;
import com.devsu.business.customer.repository.CustomerRepository;
import com.devsu.business.customer.service.CustomerService;
import com.devsu.business.customer.service.dto.CustomerDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Override
    public CustomerDto getById(long id) {
        return null;
    }

    @Override
    public CustomerDto getByIdentificationNumber(String identificationNumber) {
        return null;
    }

    @Override
    public List<CustomerDto> getAll() {
        return List.of();
    }

    @Override
    public CustomerDto save(Customer customer) {
        return null;
    }

    @Override
    public CustomerDto update(Customer customer) {
        return null;
    }

    @Override
    public CustomerDto delete(long id) {
        return null;
    }
}
