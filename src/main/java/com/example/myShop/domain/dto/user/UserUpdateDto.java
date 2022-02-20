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
@Schema(name = "UserUpdate", description = "Fields of user that can be updated")
public class UserUpdateDto {
    @Schema(description = "Name of user")
    String name;
    @Schema(description = "Phone number of user",
            maxLength = 12,
            minLength = 11)
    String phone;
    @Schema(description = "Email of user")
    String email;
    @Schema(description = "Password of user",
            minLength = 6,
            maxLength = 100)
    String password;
}
