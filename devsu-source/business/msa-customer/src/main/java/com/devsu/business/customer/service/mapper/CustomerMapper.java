package com.devsu.business.customer.service.mapper;

import com.devsu.business.customer.domain.Customer;
import com.devsu.business.customer.service.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends PersonMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
}
