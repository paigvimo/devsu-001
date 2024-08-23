package com.devsu.business.customer.service.mapper;

import com.devsu.business.customer.domain.Person;
import com.devsu.business.customer.service.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    PersonDto toPersonDto(Person person);
    Person toPerson(PersonDto personDto);
}
