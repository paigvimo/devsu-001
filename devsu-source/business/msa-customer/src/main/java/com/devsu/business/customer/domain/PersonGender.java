package com.devsu.business.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum PersonGender {
    M("MALE"),
    F("FEMALE");

    private PersonGender(String description) {
        this.description = description;
    }
    final String description;
}
