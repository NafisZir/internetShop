package com.example.myShop.controller;

import com.example.myShop.domain.dto.order.OrderDto;
import com.example.myShop.domain.dto.order.OrderInfoDto;
import com.example.myShop.domain.dto.order.OrderUpdateDto;
import com.example.myShop.domain.exception.OrderNotFoundException;
import com.example.myShop.domain.mapper.OrderMapper;
import com.example.myShop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("orders/{orderId}")
    public OrderDto get(@PathVariable(name = "orderId") Integer id){
        return Optional.of(id)
                .map(orderService::getAndInitialize)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @GetMapping("orders/info/{id}")
    public OrderInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(orderService::getAndInitialize)
                .map(orderMapper::toInfoDto)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @GetMapping("users/{userId}/orders")
    public Page<OrderDto> getAllByUserId(@PathVariable("userId") Integer userId, Pageable pageable){
        return Optional.of(userId)
                .map(id -> orderService.getAllByUserIdAndInit(id, pageable))
                .map(it -> it.map(orderMapper::toDto))
                .orElseThrow();
    }

    @GetMapping("receivings/{receivingId}/orders")
    public Page<OrderDto> getAllByReceivingId(@PathVariable("receivingId") Integer receivingId, Pageable pageable){
        return Optional.of(receivingId)
                .map(id -> orderService.getAllByReceivingIdAndInit(id, pageable))
                .map(it -> it.map(orderMapper::toDto))
                .orElseThrow();
    }

    @PatchMapping("orders/{orderId}")
    public OrderDto update(@PathVariable(name = "orderId") Integer id,
                           @RequestParam(value = "receivingId", required = false) Integer receivingId,
                           @RequestBody OrderUpdateDto orderDto){
        return Optional.ofNullable(orderDto)
                .map(orderMapper::fromUpdateDto)
                .map(toUpdate -> orderService.update(id, toUpdate, receivingId))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("orders/{orderId}")
    public void delete(@PathVariable(name = "orderId") Integer id){
        orderService.delete(id);
    }
}
