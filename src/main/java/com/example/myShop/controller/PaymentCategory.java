package com.example.myShop.controller;

import com.example.myShop.domain.entity.Payment;
import com.example.myShop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Controller
@RequestMapping(path = "payments")
@RequiredArgsConstructor
public class PaymentCategory {
    private final PaymentService paymentService;

    @GetMapping("/admin")
    public String getPayments(Model model){
        model.addAttribute("payments", paymentService.getPayments());
        return "admin-payments";
    }

    @PostMapping()
    public String create(Payment payment){
        paymentService.create(payment);
        return "redirect:/payments/admin";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") String id){
        paymentService.delete(id);
        return "redirect:/payments/admin";
    }
}
