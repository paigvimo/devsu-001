package com.devsu.business.customer.repository;

import com.devsu.business.customer.domain.Customer;
import com.devsu.business.customer.domain.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdentificationNumber(String identificationNumber);
    List<Customer> findByStatusNot(CustomerStatus customerStatus);
}
