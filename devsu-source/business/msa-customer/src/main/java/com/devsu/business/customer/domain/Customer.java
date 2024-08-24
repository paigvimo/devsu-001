package com.devsu.business.customer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "TCUSTOMER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@lombok.Getter
@lombok.Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Customer extends Person {
    @Column(length = 3, nullable = false,  name = "STATUS")
    @Enumerated(EnumType.STRING)
    CustomerStatus status;
    @Column(length = 3000, nullable = false, name = "PASSWORD")
    String password;
}
