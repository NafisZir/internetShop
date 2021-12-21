package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.UserDto;
import com.example.myShop.domain.dto.UserNotIdDto;
import com.example.myShop.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User fromNotIdDto(UserNotIdDto source);

    UserDto toDto(User source);
}
