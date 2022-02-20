package com.example.myShop.domain.dto.producer;

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
 * @since 29.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "ProducerCreate", description = "Fields requires to create producer of goods")
public class ProducerCreateDto {
    @NotBlank(message = "{producer.name.empty}")
    @Size(max = 20)
    @Schema(description = "Producer name",
            required = true)
    String name;

    @NotBlank(message = "{producer.country.empty}")
    @Size(max = 20)
    @Schema(description = "Producer country",
            required = true)
    String country;
}
