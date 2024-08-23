package com.devsu.business.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum CustomerStatus {
    ACT("ACTIVATED"),
    INA("INACTIVE"),
    BLK("BLOCKED"),
    DEL("DELETED");
    private CustomerStatus (String statusDescription) {
        this.statusDescription = statusDescription;
    }
    final String statusDescription;
}
