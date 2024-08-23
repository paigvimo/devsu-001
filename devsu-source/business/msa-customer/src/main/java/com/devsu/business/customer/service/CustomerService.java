package com.devsu.business.customer.service;

import com.devsu.business.customer.domain.Customer;
import com.devsu.business.customer.service.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getById(long id);
    CustomerDto getByIdentificationNumber(String identificationNumber);
    List<CustomerDto> getAll();
    CustomerDto save(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto);
    boolean delete(String identificationNumber);
}
