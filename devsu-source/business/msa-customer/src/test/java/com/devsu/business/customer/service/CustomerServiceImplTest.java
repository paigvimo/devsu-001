package com.devsu.business.customer.service;

import com.devsu.business.customer.domain.Customer;
import com.devsu.business.customer.domain.CustomerStatus;
import com.devsu.business.customer.exception.CustomerNotFoundException;
import com.devsu.business.customer.repository.CustomerRepository;
import com.devsu.business.customer.service.dto.CustomerDto;
import com.devsu.business.customer.service.impl.CustomerServiceImpl;
import com.devsu.business.customer.service.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById_Success() {
        long customerId = 1L;
        Customer customer = new Customer();
        CustomerDto customerDto = new CustomerDto();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getById(customerId);

        assertNotNull(result);
        verify(customerRepository).findById(customerId);
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testGetById_NotFound() {
        long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getById(customerId));
        verify(customerRepository).findById(customerId);
    }

    @Test
    void testGetByIdentificationNumber_Success() {
        String identificationNumber = "12345";
        Customer customer = new Customer();
        CustomerDto customerDto = new CustomerDto();
        when(customerRepository.findByIdentificationNumber(identificationNumber)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getByIdentificationNumber(identificationNumber);

        assertNotNull(result);
        verify(customerRepository).findByIdentificationNumber(identificationNumber);
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testGetByIdentificationNumber_NotFound() {
        String identificationNumber = "12345";
        when(customerRepository.findByIdentificationNumber(identificationNumber)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getByIdentificationNumber(identificationNumber));
        verify(customerRepository).findByIdentificationNumber(identificationNumber);
    }

    @Test
    void testGetAll() {
        List<Customer> customerList = Arrays.asList(new Customer(), new Customer());
        CustomerDto customerDto = new CustomerDto();
        when(customerRepository.findByStatusNot(CustomerStatus.DEL)).thenReturn(customerList);
        when(customerMapper.toDto(any(Customer.class))).thenReturn(customerDto);

        List<CustomerDto> result = customerService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(customerRepository).findByStatusNot(CustomerStatus.DEL);
        verify(customerMapper, times(2)).toDto(any(Customer.class));
    }

    @Test
    void testSave_Success() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setIdentificationNumber("12345");
        Customer customer = new Customer();
        when(customerRepository.findByIdentificationNumber(customerDto.getIdentificationNumber())).thenReturn(Optional.empty());
        when(customerMapper.toEntity(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.save(customerDto);

        assertNotNull(result);
        verify(customerRepository).findByIdentificationNumber(customerDto.getIdentificationNumber());
        verify(customerMapper).toEntity(customerDto);
        verify(customerRepository).save(customer);
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testSave_CustomerAlreadyExists() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setIdentificationNumber("12345");
        Customer customer = new Customer();
        when(customerRepository.findByIdentificationNumber(customerDto.getIdentificationNumber())).thenReturn(Optional.of(customer));

        assertThrows(CustomerNotFoundException.class, () -> customerService.save(customerDto));
        verify(customerRepository).findByIdentificationNumber(customerDto.getIdentificationNumber());
    }

    @Test
    void testUpdate_Success() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setIdentificationNumber("12345");
        Customer customer = new Customer();
        when(customerRepository.findByIdentificationNumber(customerDto.getIdentificationNumber())).thenReturn(Optional.of(customer));
        when(customerMapper.toEntity(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.update(customerDto);

        assertNotNull(result);
        verify(customerRepository).findByIdentificationNumber(customerDto.getIdentificationNumber());
        verify(customerMapper).toEntity(customerDto);
        verify(customerRepository).save(customer);
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testUpdate_CustomerNotFound() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setIdentificationNumber("12345");
        when(customerRepository.findByIdentificationNumber(customerDto.getIdentificationNumber())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.update(customerDto));
        verify(customerRepository).findByIdentificationNumber(customerDto.getIdentificationNumber());
    }

    @Test
    void testDelete_Success() {
        String identificationNumber = "12345";
        Customer customer = new Customer();
        when(customerRepository.findByIdentificationNumber(identificationNumber)).thenReturn(Optional.of(customer));

        boolean result = customerService.delete(identificationNumber);

        assertTrue(result);
        verify(customerRepository).findByIdentificationNumber(identificationNumber);
        verify(customerRepository).save(customer);
        assertEquals(CustomerStatus.DEL, customer.getStatus());
    }

    @Test
    void testDelete_CustomerNotFound() {
        String identificationNumber = "12345";
        when(customerRepository.findByIdentificationNumber(identificationNumber)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.delete(identificationNumber));
        verify(customerRepository).findByIdentificationNumber(identificationNumber);
    }
}
