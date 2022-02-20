package com.example.myShop.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 22.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "UserInfo", description = "Info about user")
public class UserInfoDto {
    @Schema(description = "User id",
            required = true)
    Integer id;
    @Schema(description = "Name of user",
            required = true)
    String name;
    @Schema(description = "Phone number of user",
            maxLength = 12,
            minLength = 11)
    String phone;
    @Schema(description = "Email of user",
            required = true)
    String email;
    @Schema(description = "Password of user",
            required = true,
            minLength = 6,
            maxLength = 100)
    String password;
    @Schema(description = "Role of user",
            required = true)
    String role;
}
