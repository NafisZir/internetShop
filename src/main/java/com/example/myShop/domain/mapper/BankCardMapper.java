package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.bankCard.BankCardCreateDto;
import com.example.myShop.domain.dto.bankCard.BankCardDto;
import com.example.myShop.domain.dto.bankCard.BankCardInfoDto;
import com.example.myShop.domain.dto.bankCard.BankCardUpdateDto;
import com.example.myShop.domain.entity.BankCard;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author nafis
 * @since 10.02.2022
 */

@Mapper
public interface BankCardMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    BankCard fromCreateDto(BankCardCreateDto bankCardCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    BankCard fromUpdateDto(BankCardUpdateDto bankCardCreateDto);

    BankCardDto toDto(BankCard bankCard);

    @Mapping(target = "userId", source = "user.id")
    BankCardInfoDto toBankCardInfoDto(BankCard bankCard);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    BankCard merge(@MappingTarget BankCard target, BankCard source);
}
