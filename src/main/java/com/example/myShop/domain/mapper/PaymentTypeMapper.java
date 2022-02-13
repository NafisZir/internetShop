package com.example.myShop.domain.mapper;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.dto.CollectionWrapperDto;
import com.example.myShop.domain.dto.paymentType.PaymentTypeDto;
import com.example.myShop.domain.enums.PaymentType;
import org.mapstruct.Mapper;

/**
 * @author nafis
 * @since 13.02.2022
 */

@Mapper
public interface PaymentTypeMapper {
    PaymentTypeDto toDto(PaymentType paymentType);

    CollectionWrapperDto<PaymentTypeDto> toWrapper(CollectionWrapper<PaymentType> collection);
}
