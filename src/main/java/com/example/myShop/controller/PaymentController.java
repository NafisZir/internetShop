package com.example.myShop.controller;

import com.example.myShop.domain.dto.payment.PaymentDto;
import com.example.myShop.domain.dto.payment.PaymentCreateDto;
import com.example.myShop.domain.dto.payment.PaymentUpdateDto;
import com.example.myShop.domain.exception.PaymentNotFoundException;
import com.example.myShop.domain.mapper.PaymentMapper;
import com.example.myShop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequestMapping(path = "payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @GetMapping("{id}")
    public PaymentDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(paymentService::get)
                .map(paymentMapper::toDto)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    @PostMapping()
    public PaymentDto create(@RequestBody PaymentCreateDto paymentDto){
        return Optional.ofNullable(paymentDto)
                .map(paymentMapper::fromCreateDto)
                .map(paymentService::create)
                .map(paymentMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("{id}")
    public PaymentDto update(@PathVariable("id") Integer id, PaymentUpdateDto paymentDto){
        return Optional.ofNullable(paymentDto)
                .map(paymentMapper::fromUpdateDto)
                .map(toUpdate -> paymentService.update(toUpdate, id))
                .map(paymentMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        paymentService.delete(id);
    }
}
