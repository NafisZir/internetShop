package com.example.myShop.domain.dto.user;

import com.example.myShop.validation.annotation.Email;
import com.example.myShop.validation.annotation.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "UserCreate", description = "Fields requires to create user")
public class UserCreateDto {
    @NotBlank(message = "{user.name.empty}")
    @Size(max = 20)
    @Schema(description = "Name of user",
            required = true)
    String name;

    @Phone
    @Schema(description = "Phone number of user",
            maxLength = 12,
            minLength = 11)
    String phone;

    @Email
    @Schema(description = "Email of user",
            required = true)
    String email;

    @NotBlank
    @Size(min = 6, message = "{user.password.min}")
    @Size(max = 100, message = "{user.password.max}")
    @Schema(description = "Password of user",
            required = true,
            minLength = 6,
            maxLength = 100)
    String password;

    @Schema(description = "Role of user",
            required = true)
    String role;
}
