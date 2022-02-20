package com.example.myShop.domain.dto.bankCard;

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
@Schema(name = "BankCardUpdate", description = "Fields of bank card that can be updated")
public class BankCardUpdateDto {
    @Schema(description = "Bank card number",
            minLength = 13,
            maxLength = 19)
    String number;
    @Schema(description = "Bank card month of expiration")
    Byte month;
    @Schema(description = "Bank card year of expiration")
    Byte year;
    @Schema(description = "Bank card back number",
            minLength = 3,
            maxLength = 3)
    Short backNumber;
}
