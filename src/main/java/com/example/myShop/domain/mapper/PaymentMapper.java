package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.payment.PaymentCreateDto;
import com.example.myShop.domain.dto.payment.PaymentDto;
import com.example.myShop.domain.dto.payment.PaymentInfoDto;
import com.example.myShop.domain.dto.payment.PaymentUpdateDto;
import com.example.myShop.domain.entity.Payment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface PaymentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Payment fromCreateDto(PaymentCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Payment fromUpdateDto(PaymentUpdateDto source);

    PaymentDto toDto(Payment source);

    PaymentInfoDto toInfoDto(Payment source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Payment merge(@MappingTarget Payment target, Payment source);
}
