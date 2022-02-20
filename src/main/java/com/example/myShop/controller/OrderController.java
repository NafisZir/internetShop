package com.example.myShop.controller;

import com.example.myShop.domain.dto.order.OrderDto;
import com.example.myShop.domain.dto.order.OrderInfoDto;
import com.example.myShop.domain.dto.order.OrderUpdateDto;
import com.example.myShop.domain.exception.OrderNotFoundException;
import com.example.myShop.domain.mapper.OrderMapper;
import com.example.myShop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author nafis
 * @since 19.12.2021
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Order", description = "Operations with order of user which contains goods")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "404", description = "Order not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Validation failed or bad request")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(description = "Find order by id")
    @ApiResponse(responseCode = "200", description = "Order found successfully")
    @GetMapping("orders/{orderId}")
    public OrderDto get(@PathVariable(name = "orderId") Integer id){
        return Optional.of(id)
                .map(orderService::getAndInitialize)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Operation(description = "Find information about order by id")
    @ApiResponse(responseCode = "200", description = "Order found successfully")
    @GetMapping("orders/info/{id}")
    public OrderInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(orderService::getAndInitialize)
                .map(orderMapper::toInfoDto)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Operation(description = "Find all orders by user id. Returns page")
    @ApiResponse(responseCode = "200", description = "Orders found successfully")
    @GetMapping("users/{userId}/orders")
    public Page<OrderDto> getAllByUserId(@PathVariable("userId") Integer userId, Pageable pageable){
        return Optional.of(userId)
                .map(id -> orderService.getAllByUserIdAndInit(id, pageable))
                .map(it -> it.map(orderMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Find all orders by receiving id. Returns page")
    @ApiResponse(responseCode = "200", description = "Orders found successfully")
    @GetMapping("receivings/{receivingId}/orders")
    public Page<OrderDto> getAllByReceivingId(@PathVariable("receivingId") Integer receivingId, Pageable pageable){
        return Optional.of(receivingId)
                .map(id -> orderService.getAllByReceivingIdAndInit(id, pageable))
                .map(it -> it.map(orderMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Update order by id")
    @ApiResponse(responseCode = "200", description = "Order updated successfully")
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

    @Operation(description = "Remove order by id")
    @ApiResponse(responseCode = "204", description = "Order removed successfully")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("orders/{orderId}")
    public void delete(@PathVariable(name = "orderId") Integer id){
        orderService.delete(id);
    }
}
