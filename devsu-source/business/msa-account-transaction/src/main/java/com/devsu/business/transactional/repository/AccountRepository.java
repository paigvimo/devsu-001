package com.devsu.business.transactional.repository;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByStatusNot(AccountStatus accountStatus);
}
