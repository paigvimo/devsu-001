package com.devsu.business.customer.mocks;

import static org.mockito.Mockito.*;
import com.devsu.business.customer.domain.Customer;
import com.devsu.business.customer.domain.CustomerStatus;
import com.devsu.business.customer.domain.PersonGender;

import java.util.Date;

import static org.mockito.Mockito.*;
import com.devsu.business.customer.domain.Customer;
import com.devsu.business.customer.domain.CustomerStatus;
import com.devsu.business.customer.service.dto.CustomerDto;
import com.devsu.business.customer.domain.PersonGender;

import java.util.Date;

public class CustomerMock {

    public static Customer mockCustomerEntity() {
        Customer customer = mock(Customer.class);

        when(customer.getPersonId()).thenReturn(1L);
        when(customer.getIdentificationNumber()).thenReturn("123456789");
        when(customer.getName()).thenReturn("John Doe");
        when(customer.getGender()).thenReturn(PersonGender.M);
        when(customer.getBirthdate()).thenReturn(new Date());
        when(customer.getAddress()).thenReturn("123 Main St");
        when(customer.getTelephone()).thenReturn("555-1234");
        when(customer.getStatus()).thenReturn(CustomerStatus.ACT);
        when(customer.getPassword()).thenReturn("password");

        return customer;
    }

    public static CustomerDto mockCustomerDto() {
        CustomerDto customerDto = mock(CustomerDto.class);

        when(customerDto.getPersonId()).thenReturn(1L);
        when(customerDto.getIdentificationNumber()).thenReturn("123456789");
        when(customerDto.getName()).thenReturn("John Doe");
        when(customerDto.getGender()).thenReturn(PersonGender.M);
        when(customerDto.getBirthdate()).thenReturn(new Date());
        when(customerDto.getAddress()).thenReturn("123 Main St");
        when(customerDto.getTelephone()).thenReturn("555-1234");
        when(customerDto.getStatus()).thenReturn(CustomerStatus.ACT);
        when(customerDto.getPassword()).thenReturn("password");

        return customerDto;
    }
}
