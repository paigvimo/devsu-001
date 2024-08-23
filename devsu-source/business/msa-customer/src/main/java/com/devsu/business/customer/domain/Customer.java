package com.devsu.business.customer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "TCUSTOMER")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 8, name = "CUSTOMER_ID")
    long customerId;
    @Column(length = 3, nullable = false,  name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    CustomerStatus status;
    @Column(length = 3000, nullable = false, name = "PASSWORD")
    String password;
}
