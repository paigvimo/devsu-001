package com.devsu.business.transactional.service.mapper;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.service.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AccountDto toDto(Account account);
    Account toEntity(AccountDto accountDto);
}
