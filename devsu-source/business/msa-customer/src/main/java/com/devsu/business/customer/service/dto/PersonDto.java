package com.devsu.business.customer.service.dto;

import com.devsu.business.customer.domain.PersonGender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.EnumMapping;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    long personId;
    String identificationNumber;
    String name;
    @Enumerated(EnumType.STRING)
    PersonGender gender;
    Date birthdate;
    String address;
    String telephone;
}
