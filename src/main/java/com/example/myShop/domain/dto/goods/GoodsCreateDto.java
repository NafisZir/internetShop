package com.example.myShop.domain.dto.goods;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;
import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "GoodsCreate", description = "Fields requires to create goods")
public class GoodsCreateDto {
    @NotBlank(message = "{goods.name.empty}")
    @Size(max = 20, message = "{goods.name.tooLong}")
    @Schema(description = "Goods name",
            required = true)
    String name;

    @NotNull
    @Min(value = 0)
    @Schema(description = "Price of goods",
            required = true)
    BigDecimal price;

    @Min(value = 0)
    @Max(value = 9223372036854775807L)
    @Schema(description = "Count of goods",
            required = true,
            minimum = "0",
            maximum = "9223372036854775807L")
    long count;

    @NotBlank(message = "{goods.imageUrl.empty}")
    @Size(max = 255)
    @Schema(description = "Image url of goods",
            required = true)
    String imageUrl;
}
