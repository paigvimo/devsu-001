package com.devsu.business.customer.mocks;

import static org.mockito.Mockito.*;

import com.devsu.business.customer.domain.Person;
import com.devsu.business.customer.service.dto.PersonDto;
import com.devsu.business.customer.domain.PersonGender;

import java.util.Date;

public class PersonMock {

    public static PersonDto mockPersonDto() {
        PersonDto personDto = mock(PersonDto.class);

        when(personDto.getPersonId()).thenReturn(2L);
        when(personDto.getIdentificationNumber()).thenReturn("987654321");
        when(personDto.getName()).thenReturn("Jane Doe");
        when(personDto.getGender()).thenReturn(PersonGender.F);
        when(personDto.getBirthdate()).thenReturn(new Date());
        when(personDto.getAddress()).thenReturn("456 Another St");
        when(personDto.getTelephone()).thenReturn("555-6789");

        return personDto;
    }

    public static Person mockPersonEntity() {
        Person person = mock(Person.class);

        when(person.getPersonId()).thenReturn(2L);
        when(person.getIdentificationNumber()).thenReturn("987654321");
        when(person.getName()).thenReturn("Jane Doe");
        when(person.getGender()).thenReturn(PersonGender.F);
        when(person.getBirthdate()).thenReturn(new Date());
        when(person.getAddress()).thenReturn("456 Another St");
        when(person.getTelephone()).thenReturn("555-6789");

        return person;
    }
}
