package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.bankCard.BankCardInfoDto;
import com.example.myShop.domain.dto.user.UserCreateDto;
import com.example.myShop.domain.dto.user.UserDto;
import com.example.myShop.domain.dto.user.UserInfoDto;
import com.example.myShop.domain.dto.user.UserUpdateDto;
import com.example.myShop.domain.entity.BankCard;
import com.example.myShop.domain.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper(componentModel = "spring", uses = {BankCardMapper.class})
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "bankCards", ignore = true)
    User fromCreateDto(UserCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "bankCards", ignore = true)
    User fromUpdateDto(UserUpdateDto source);

    UserDto toDto(User source);

    UserInfoDto toInfoDto(User source);

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "bankCards", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    User merge(@MappingTarget User target, User source);

    List<BankCardInfoDto> toBankCardInfoDtoList(List<BankCard> list);
}
