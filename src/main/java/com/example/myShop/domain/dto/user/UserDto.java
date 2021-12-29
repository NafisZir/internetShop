package com.example.myShop.domain.dto.user;

import com.example.myShop.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class UserDto {
    int id;
    String name;
    String phone;
    String email;
    String password;

    List<Order> orders;
}
