package com.example.myShop.domain.dto.producer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "Producer", description = "Full info about producer")
public class ProducerDto {
    @Schema(description = "Producer id",
            required = true)
    Integer id;
    @Schema(description = "Producer name",
            required = true)
    String name;
    @Schema(description = "Producer country",
            required = true)
    String country;
}
