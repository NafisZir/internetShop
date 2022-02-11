package com.example.myShop.domain.dto.bankCard;

import com.example.myShop.domain.dto.user.UserInfoDto;
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
public class BankCardDto {
    Integer id;
    String number;
    Byte month;
    Byte year;
    Short backNumber;

    UserInfoDto user;
}
