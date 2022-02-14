package com.example.myShop.controller;

import com.example.myShop.domain.dto.collectionWrapper.CollectionWrapperDto;
import com.example.myShop.domain.dto.orderStatus.OrderStatusDto;
import com.example.myShop.domain.mapper.OrderStatusMapper;
import com.example.myShop.service.OrderStatusService;
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
public class OrderStatusController {
    private final OrderStatusService orderStatusService;
    private final OrderStatusMapper orderStatusMapper;

    @GetMapping()
    public CollectionWrapperDto<OrderStatusDto> getAll(){
        return Optional.of(orderStatusService.getAllAndWrap())
                .map(orderStatusMapper::toWrapperDto)
                .orElseThrow();
    }
}
