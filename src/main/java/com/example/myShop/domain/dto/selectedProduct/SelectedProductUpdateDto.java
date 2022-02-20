package com.example.myShop.domain.dto.selectedProduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 09.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "SelectedProductUpdate", description = "Fields of selected product that can be updated")
public class SelectedProductUpdateDto {
    @Schema(description = "Count of goods in selected product",
            required = true)
    Integer count;
}
