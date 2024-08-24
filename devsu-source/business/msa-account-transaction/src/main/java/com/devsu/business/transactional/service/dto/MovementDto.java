package com.devsu.business.transactional.service.dto;

import com.devsu.business.transactional.domain.enums.TransactionType;
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
public class MovementDto {

    Long accountNumber;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;
}
