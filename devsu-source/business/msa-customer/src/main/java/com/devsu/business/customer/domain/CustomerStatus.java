package com.devsu.business.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum CustomerStatus {
    ACTIVE("ACT", "ACTIVATED"),
    INACTIVE("INA", "INACTIVE"),
    BLOCKED("BLK", "BLOCKED"),
    DELETED("DEL", "DELETED");
    private CustomerStatus (String statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }
    final String statusCode;
    final String statusDescription;
}
