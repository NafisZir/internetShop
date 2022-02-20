package com.example.myShop.domain.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 19.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "LoginRequest", description = "Contains required fields to login user")
public class LoginRequest {
    @Schema(description = "Email of user",
            required = true)
    String username;
    @Schema(description = "Password of user",
            required = true)
    String password;
}
