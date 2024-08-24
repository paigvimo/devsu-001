package com.devsu.business.transactional.repository;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findFirstByAccountOrderByTransactionDateDesc(@Param("account")Account account);

    List<Transaction> findByAccountAndTransactionDateBetween(Account account, Date startDate, Date endDate);
}
