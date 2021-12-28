package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.user.UserCreateDto;
import com.example.myShop.domain.dto.user.UserDto;
import com.example.myShop.domain.dto.user.UserInfoDto;
import com.example.myShop.domain.dto.user.UserUpdateDto;
import com.example.myShop.domain.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User fromCreateDto(UserCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User fromUpdateDto(UserUpdateDto source);

    UserDto toDto(User source);

    UserInfoDto toInfoDto(User source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "authorities", ignore = true)
    User merge(@MappingTarget User target, User source);
}
