package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.Payment;
import com.example.myShop.repository.PaymentRepository;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ordering.antlr.OrderingSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    @Override
    public Payment get(String id){
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getPayments(){
        return paymentRepository.findAll();
    }

    @Override
    public void create(Payment payment){
        paymentRepository.save(payment);
    }

    @Override
    public void update(Payment payment) { paymentRepository.save(payment); }

    @Override
    public void delete(String id){
        List<Order> orderList = orderService.getOrdersByPayMethod(id);

        if(orderList.isEmpty()){
            paymentRepository.deleteById(id);
        }
    }
}
