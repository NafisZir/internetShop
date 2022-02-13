package com.example.myShop.controller;

import com.example.myShop.domain.dto.CollectionWrapperDto;
import com.example.myShop.domain.dto.paymentType.PaymentTypeDto;
import com.example.myShop.domain.mapper.PaymentTypeMapper;
import com.example.myShop.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "payment-types")
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;
    private final PaymentTypeMapper paymentTypeMapper;

    @GetMapping
    public CollectionWrapperDto<PaymentTypeDto> getAll() {
        return Optional.of(paymentTypeService.getAllAndWrap())
                .map(paymentTypeMapper::toWrapper)
                .orElseThrow();
    }
}
