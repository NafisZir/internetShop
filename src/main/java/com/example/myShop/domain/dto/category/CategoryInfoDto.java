package com.example.myShop.domain.dto.category;

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
@Schema(name = "CategoryInfo", description = "Info about category of goods")
public class CategoryInfoDto {
    @Schema(description = "Category id",
            required = true)
    int id;
    @Schema(description = "Category name",
            required = true)
    String name;
}
