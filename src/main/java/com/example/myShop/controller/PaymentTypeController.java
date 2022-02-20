package com.example.myShop.controller;

import com.example.myShop.domain.dto.collectionWrapper.CollectionWrapperDto;
import com.example.myShop.domain.dto.paymentType.PaymentTypeDto;
import com.example.myShop.domain.mapper.PaymentTypeMapper;
import com.example.myShop.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Payment type", description = "Operations with payment type of order")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Bad request")
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;
    private final PaymentTypeMapper paymentTypeMapper;

    @Operation(description = "Find all payment types")
    @ApiResponse(responseCode = "200", description = "Payment types found successfully")
    @GetMapping
    public CollectionWrapperDto<PaymentTypeDto> getAll() {
        return Optional.of(paymentTypeService.getAllAndWrap())
                .map(paymentTypeMapper::toWrapperDto)
                .orElseThrow();
    }
}
