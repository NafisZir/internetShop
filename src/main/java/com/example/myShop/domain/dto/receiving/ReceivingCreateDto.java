package com.example.myShop.domain.dto.receiving;

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
@Schema(name = "ReceivingCreate", description = "Fields requires to create receiving")
public class ReceivingCreateDto {
    @NotBlank(message = "{receiving.receiveMethod.empty}")
    @Size(max = 20)
    @Schema(description = "Method of receiving order",
            required = true)
    String receiveMethod;

    @NotBlank(message = "{receiving.address.empty}")
    @Size(max = 100)
    @Schema(description = "Receiving address",
            required = true)
    String address;
}
