package com.devsu.business.customer.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@MappedSuperclass
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_ID", length = 8)
    long personId;
    @Column(name = "IDENTIFICATION_NUMBER", nullable = false, length = 20)
    String identificationNumber;
    @Column(name = "NAME", length = 300)
    String name;
    @Column(name = "GENDER", nullable = false,  length = 1)
    @Enumerated(EnumType.STRING)
    PersonGender gender;
    @Column(name = "BIRTHDATE", nullable = false)
    Date birthdate;
    @Column(name = "ADDRESS", nullable = false, length = 250)
    String address;
    @Column(name = "TELEPHONE", nullable = false, length = 20)
    String telephone;
}
