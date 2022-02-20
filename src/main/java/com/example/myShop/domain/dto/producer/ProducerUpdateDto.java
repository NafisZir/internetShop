package com.example.myShop.domain.dto.producer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 29.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "ProducerUpdate", description = "Fields of producer that can be updated")
public class ProducerUpdateDto {
    @Schema(description = "Producer name")
    String name;
    @Schema(description = "Producer country")
    String country;
}
