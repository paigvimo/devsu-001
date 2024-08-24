package com.devsu.business.transactional.domain;

import com.devsu.business.transactional.domain.enums.AccountStatus;
import com.devsu.business.transactional.domain.enums.AccountType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "TACCOUNT")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID", length = 8)
    Long accountId;

    @Column(name = "CUSTOMER_ID", length = 8, nullable = false)
    Long customerId;

    @Column(name = "ACCOUNT_TYPE", length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @Column(name = "INITIAL_AMOUNT", nullable = false, precision = 19, scale = 11)
    BigDecimal initialAmount;

    @Column(name = "ACCOUNT_STATUS", nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;
}
