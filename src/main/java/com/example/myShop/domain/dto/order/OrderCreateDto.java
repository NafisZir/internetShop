package com.example.myShop.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class OrderCreateDto {
    @Min(value = 1, message = "{order.count.min}")
    @Max(value = 10, message = "{order.count.max}")
    int count;
}
