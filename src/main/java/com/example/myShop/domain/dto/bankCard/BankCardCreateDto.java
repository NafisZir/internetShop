package com.example.myShop.domain.dto.bankCard;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "BankCardCreate", description = "Fields requires to create bank card")
public class BankCardCreateDto {
    @Size(min = 13, message = "{bankCard.number.min}")
    @Size(max = 19, message = "{bankCard.number.max}")
    @Schema(description = "Bank card number",
            required = true,
            minLength = 13,
            maxLength = 19)
    String number;

    @Min(value = 1, message = "{bankCard.month.min}")
    @Max(value = 12, message = "{bankCard.month.max}")
    @Schema(description = "Bank card month of expiration",
            required = true)
    Byte month;

    @Min(value = 0, message = "{bankCard.year.min}")
    @Max(value = 99, message = "{bankCard.year.max}")
    @Schema(description = "Bank card year of expiration",
            required = true)
    Byte year;

    @Min(value = 100, message = "{bankCard.backNumber.min}")
    @Max(value = 999, message = "{bankCard.backNumber.max}")
    @Schema(description = "Bank card back number",
            required = true,
            minLength = 3,
            maxLength = 3)
    Short backNumber;
}
