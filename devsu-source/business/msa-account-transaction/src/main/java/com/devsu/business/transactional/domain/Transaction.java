package com.devsu.business.transactional.domain;

import com.devsu.business.transactional.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TTRANSACTION")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID", length = 8)
    Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    Account account;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    Date transactionDate;

    @Column(name = "TRANSACTION_TYPE", length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    @Column(name = "AMOUNT", nullable = false, precision = 19, scale = 11)
    BigDecimal amount;

    @Column(name = "BALANCE", nullable = false, precision = 19, scale = 11)
    BigDecimal balance;
}
