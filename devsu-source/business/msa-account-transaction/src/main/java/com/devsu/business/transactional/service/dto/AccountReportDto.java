package com.devsu.business.transactional.service.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AccountReportDto {
    Long accountNumber;
    BigDecimal balance;
    List<TransactionDto> transactions;
}
