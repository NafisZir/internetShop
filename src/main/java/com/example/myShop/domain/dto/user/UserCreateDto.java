package com.example.myShop.domain.dto.user;

import com.example.myShop.validation.annotation.Email;
import com.example.myShop.validation.annotation.Phone;
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
public class UserCreateDto {
    @NotBlank(message = "{user.name.empty}")
    @Size(max = 20)
    String name;

    @Phone
    String phone;

    @Email
    String email;

    @NotBlank
    @Size(min = 6, message = "{user.password.min}")
    @Size(max = 100, message = "{user.password.max}")
    String password;
}
