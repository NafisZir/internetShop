package com.example.myShop.domain.dto.category;

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
@Schema(name = "CategoryCreate", description = "Fields requires to create category")
public class CategoryCreateDto {
    @NotBlank(message = "{category.name.empty}")
    @Size(max = 100)
    @Schema(description = "Category name",
            required = true)
    String name;
}
