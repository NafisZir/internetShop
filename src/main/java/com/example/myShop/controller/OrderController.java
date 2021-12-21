package com.example.myShop.controller;

import com.example.myShop.domain.dto.OrderDto;
import com.example.myShop.domain.dto.OrderNotIdDto;
import com.example.myShop.domain.mapper.OrderMapper;
import com.example.myShop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Controller
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
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    @PostMapping()
    public OrderDto create(@RequestBody OrderNotIdDto orderDto,
                           @RequestParam(name = "goodId") Integer goodId,
                           Principal principal){
        return Optional.ofNullable(orderDto)
                .map(orderMapper::fromNotIdDto)
                .map(toCreate -> orderService.create(toCreate, goodId, principal))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("{orderId}")
    public OrderDto update(@PathVariable(name = "orderId") Integer id, @RequestBody OrderNotIdDto orderDto){
        return Optional.ofNullable(orderDto)
                .map(orderMapper::fromNotIdDto)
                .map(toUpdate -> orderService.update(id, toUpdate))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{orderId}")
    public void delete(@PathVariable(name = "orderId") Integer id){
        orderService.delete(id);
    }
}
