package com.example.myShop.domain.dto.selectedProduct;

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
public class SelectedProductCreateDto {
    @Min(value = 1, message = "{selectedProduct.count.min}")
    @Max(value = 10, message = "{selectedProduct.count.max}")
    Integer count;
}
