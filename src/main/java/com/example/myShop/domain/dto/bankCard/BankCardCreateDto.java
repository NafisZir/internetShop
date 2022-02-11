package com.example.myShop.domain.dto.bankCard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 09.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class BankCardCreateDto {
    @Size(min = 13, message = "{bankCard.number.min}")
    @Size(max = 19, message = "{bankCard.number.max}")
    String number;

    @Min(value = 1, message = "{bankCard.month.min}")
    @Max(value = 12, message = "{bankCard.month.max}")
    Byte month;

    @Min(value = 0, message = "{bankCard.year.min}")
    @Max(value = 99, message = "{bankCard.year.max}")
    Byte year;

    @Min(value = 100, message = "{bankCard.backNumber.min}")
    @Max(value = 999, message = "{bankCard.backNumber.max}")
    Short backNumber;
}
