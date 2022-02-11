package com.example.myShop.controller;

import com.example.myShop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequestMapping(path = "payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping()
    public List<String> getAll(){
        return paymentService.getAll();
    }
}
