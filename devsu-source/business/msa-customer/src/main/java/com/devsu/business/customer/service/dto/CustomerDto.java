package com.devsu.business.customer.service.dto;

import com.devsu.business.customer.domain.CustomerStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends PersonDto {
    long customerId;
    @Enumerated(EnumType.STRING)
    CustomerStatus status;
    String password;
}
