package com.example.myShop.domain.dto.bankCard;

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
public class BankCardInfoDto {
    Integer id;
    String number;
    Byte month;
    Byte year;
    Short backNumber;

    Integer userId;
}
