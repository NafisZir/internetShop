package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.PaymentDto;
import com.example.myShop.domain.dto.PaymentNotIdDto;
import com.example.myShop.domain.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface PaymentMapper {
    @Mapping(target = "id", ignore = true)
    Payment fromNotIdDto(PaymentNotIdDto source);

    PaymentDto toDto(Payment source);
}
