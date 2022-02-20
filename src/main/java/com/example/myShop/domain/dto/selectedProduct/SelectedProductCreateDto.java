package com.example.myShop.domain.dto.selectedProduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 09.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "SelectedProductCreate", description = "Fields requires to create selected product")
public class SelectedProductCreateDto {
    @Min(value = 1, message = "{selectedProduct.count.min}")
    @Max(value = 10, message = "{selectedProduct.count.max}")
    @Schema(description = "Count of goods in selected product",
            required = true)
    Integer count;
}
