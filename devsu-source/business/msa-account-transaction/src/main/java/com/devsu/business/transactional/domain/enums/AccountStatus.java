package com.devsu.business.transactional.domain.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum AccountStatus {
    ACT("ACTIVE"),
    INA("INACTIVE"),
    BLK("BLOCKED"),
    DEL("DELETED");

    private AccountStatus(String description) {
        this.description = description;
    }
    final String description;

}
