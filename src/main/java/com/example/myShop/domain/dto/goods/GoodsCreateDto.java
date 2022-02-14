package com.example.myShop.domain.dto.goods;

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
public class GoodsCreateDto {
    @NotBlank(message = "{goods.name.empty}")
    @Size(max = 20, message = "{goods.name.tooLong}")
    String name;

    @NotNull
    @Min(value = 0)
    BigDecimal price;

    @Min(value = 0)
    @Max(value = 9223372036854775807L)
    long count;

    @NotBlank(message = "{goods.imageUrl.empty}")
    @Size(max = 255)
    String imageUrl;
}
