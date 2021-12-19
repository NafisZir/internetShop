package com.example.myShop.services;

import com.example.myShop.models.Payment;
import com.example.myShop.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> paymentList(){
        return paymentRepository.findAll();
    }

    public void savePayment(Payment payment){
        paymentRepository.save(payment);
    }

    public Payment getPaymentById(String id){
        return paymentRepository.findById(id).orElse(null);
    }

    public void deletePayment(String id){
        paymentRepository.deleteById(id);
    }
}
