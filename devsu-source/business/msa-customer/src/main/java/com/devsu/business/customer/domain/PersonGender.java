package com.devsu.business.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum PersonGender {
    MALE("M", "MALE"),
    FEMALE("F", "FEMALE");

    private PersonGender(String id, String description) {
        this.id = id;
        this.description = description;
    }

    final String id;
    final String description;
}
