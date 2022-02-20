package com.example.myShop.controller;

import com.example.myShop.domain.dto.collectionWrapper.CollectionWrapperDto;
import com.example.myShop.domain.dto.orderStatus.OrderStatusDto;
import com.example.myShop.domain.mapper.OrderStatusMapper;
import com.example.myShop.service.OrderStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author nafis
 * @since 05.02.2022
 */

@RestController
@AllArgsConstructor
@RequestMapping(path = "order-statuses")
@Tag(name = "Order status", description = "Operations with order status")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Bad request")
public class OrderStatusController {
    private final OrderStatusService orderStatusService;
    private final OrderStatusMapper orderStatusMapper;

    @Operation(description = "Find all order statuses")
    @ApiResponse(responseCode = "200", description = "Order statuses found successfully")
    @GetMapping()
    public CollectionWrapperDto<OrderStatusDto> getAll(){
        return Optional.of(orderStatusService.getAllAndWrap())
                .map(orderStatusMapper::toWrapperDto)
                .orElseThrow();
    }
}
