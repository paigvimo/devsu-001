package com.devsu.business.transactional.domain.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum TransactionType {

    DEB("DEBIT"),
    CRE("CREDIT");

    private TransactionType(String description) {
        this.description = description;
    }

    final String description;

}
