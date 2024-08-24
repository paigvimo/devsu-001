package com.devsu.business.transactional.service.dto;

import com.devsu.business.transactional.domain.enums.AccountStatus;
import com.devsu.business.transactional.domain.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    Long accountId;

    Long customerId;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    BigDecimal initialAmount;

    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;
}
