package com.example.myShop.domain.dto.user;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

/**
 * @author nafis
 * @since 22.12.2021
 */

@Value
@Setter
@Getter
@Jacksonized
@AllArgsConstructor
public class UserInfoDto {
    int id;
    String name;
    String phone;
    String email;
    String password;
}
