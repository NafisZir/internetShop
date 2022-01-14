package com.example.myShop.controller;

import com.example.myShop.domain.dto.order.OrderDto;
import com.example.myShop.domain.dto.order.OrderCreateDto;
import com.example.myShop.domain.dto.order.OrderUpdateDto;
import com.example.myShop.domain.exception.OrderNotFoundException;
import com.example.myShop.domain.mapper.OrderMapper;
import com.example.myShop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/{orderId}")
    public OrderDto get(@PathVariable(name = "orderId") Integer id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PostMapping()
    public OrderDto create(@RequestBody OrderCreateDto orderDto,
                           @RequestParam(name = "goodId") Integer goodId,
                           @RequestParam(name = "receiveId") Integer receiveId,
                           @RequestParam(name = "payId") Integer payId,
                           @RequestParam(name = "userId") Integer userId){
        return Optional.ofNullable(orderDto)
                .map(orderMapper::fromCreateDto)
                .map(toCreate -> orderService.create(toCreate, goodId, receiveId, payId, userId))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("{orderId}")
    public OrderDto update(@PathVariable(name = "orderId") Integer id, @RequestBody OrderUpdateDto orderDto){
        return Optional.ofNullable(orderDto)
                .map(orderMapper::fromUpdateDto)
                .map(toUpdate -> orderService.update(id, toUpdate))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{orderId}")
    public void delete(@PathVariable(name = "orderId") Integer id){
        orderService.delete(id);
    }
}
