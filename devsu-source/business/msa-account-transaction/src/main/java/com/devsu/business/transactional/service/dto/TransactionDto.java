package com.devsu.business.transactional.service.dto;

import com.devsu.business.transactional.domain.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    Long id;

    AccountDto account;

    Date transactionDate;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    BigDecimal amount;

    BigDecimal balance;
}
