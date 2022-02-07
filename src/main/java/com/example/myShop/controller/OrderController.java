package com.example.myShop.controller;

import com.example.myShop.domain.dto.order.OrderCreateDto;
import com.example.myShop.domain.dto.order.OrderDto;
import com.example.myShop.domain.dto.order.OrderInfoDto;
import com.example.myShop.domain.dto.order.OrderUpdateDto;
import com.example.myShop.domain.exception.OrderNotFoundException;
import com.example.myShop.domain.mapper.OrderMapper;
import com.example.myShop.domain.mapper.OrderUpdateMapper;
import com.example.myShop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
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
    private final OrderUpdateMapper orderUpdateMapper;

    @GetMapping("/{orderId}")
    public OrderDto get(@PathVariable(name = "orderId") Integer id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @GetMapping("/info/{id}")
    public OrderInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toInfoDto)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size){
        Map<String, Object> response = orderService.getAll(page, size);

        try{
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public OrderDto create(@Valid @RequestBody OrderCreateDto orderDto,
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

    @PatchMapping("/{orderId}")
    public OrderDto update(@PathVariable(name = "orderId") Integer id, @RequestBody OrderUpdateDto orderDto){
        return Optional.ofNullable(orderDto)
                .map(orderUpdateMapper::fromUpdateDto)
                .map(toUpdate -> orderService.update(id, toUpdate))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable(name = "orderId") Integer id){
        orderService.delete(id);
    }
}
