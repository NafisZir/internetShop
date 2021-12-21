package com.example.myShop.domain.dto;

import com.example.myShop.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class UserNotIdDto {
    String name;
    String phone;
    String email;
    String password;
}
