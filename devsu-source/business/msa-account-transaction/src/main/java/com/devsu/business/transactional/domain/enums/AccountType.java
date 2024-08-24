package com.devsu.business.transactional.domain.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum AccountType {
    SAV("SAVINGS"),
    CHK("CHECKING");

    private AccountType(String description) {
        this.description = description;
    }
    final String description;

}
