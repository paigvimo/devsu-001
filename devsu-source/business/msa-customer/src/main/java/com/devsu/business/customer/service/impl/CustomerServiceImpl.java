package com.devsu.business.customer.service.impl;

import com.devsu.business.customer.domain.Customer;
import com.devsu.business.customer.domain.CustomerStatus;
import com.devsu.business.customer.exception.CustomerNotFoundException;
import com.devsu.business.customer.repository.CustomerRepository;
import com.devsu.business.customer.service.CustomerService;
import com.devsu.business.customer.service.dto.CustomerDto;
import com.devsu.business.customer.service.mapper.CustomerMapper;
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
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    @Override
    public CustomerDto getById(long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return customerMapper.toDto(customerOptional.get());
        } else {
            throw new CustomerNotFoundException("CUSTOMER WITH ID {0} NOT FOUND", id);
        }
    }

    @Override
    public CustomerDto getByIdentificationNumber(String identificationNumber) {
        Optional<Customer> customerOptional = customerRepository.findByIdentificationNumber(identificationNumber);
        if (customerOptional.isPresent()) {
            return customerMapper.toDto(customerOptional.get());
        } else {
            throw new CustomerNotFoundException("CUSTOMER WITH IDENTIFICATION NUMBER {0} NOT FOUND", identificationNumber);
        }
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customerList = customerRepository.findByStatusNot(CustomerStatus.DEL);
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDtoList.add(customerMapper.toDto(customer));
        }
        return customerDtoList;
    }

    @Override
    @Transactional
    public CustomerDto save(CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findByIdentificationNumber(customerDto.getIdentificationNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerNotFoundException("CUSTOMER WITH IDENTIFICATION NUMBER {0} ALREADY EXIST", customerDto.getIdentificationNumber());
        } else {
            Customer customer = customerMapper.toEntity(customerDto);
            customer = customerRepository.save(customer);
            return customerMapper.toDto(customer);
        }
    }

    @Override
    @Transactional
    public CustomerDto update(CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findByIdentificationNumber(customerDto.getIdentificationNumber());
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("CUSTOMER WITH IDENTIFICATION NUMBER {0} NOT EXIST", customerDto.getIdentificationNumber());
        } else {
            Customer customer = customerMapper.toEntity(customerDto);
            customer = customerRepository.save(customer);
            return customerMapper.toDto(customer);
        }
    }

    @Override
    @Transactional
    public boolean delete(String identificationNumber) {
        Optional<Customer> customerOptional = customerRepository.findByIdentificationNumber(identificationNumber);
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("CUSTOMER WITH IDENTIFICATION NUMBER {0} NOT EXIST", identificationNumber);
        }
        Customer customer = customerOptional.get();
        customer.setStatus(CustomerStatus.DEL);
        customerRepository.save(customer);
        return true;
    }
}
