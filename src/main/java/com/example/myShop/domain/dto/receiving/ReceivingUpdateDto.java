package com.example.myShop.domain.dto.receiving;

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
@Schema(name = "ReceivingUpdate", description = "Fields of receiving that can be updated")
public class ReceivingUpdateDto {
    @Schema(description = "Method of receiving order")
    String receiveMethod;
    @Schema(description = "Receiving address")
    String address;
}
