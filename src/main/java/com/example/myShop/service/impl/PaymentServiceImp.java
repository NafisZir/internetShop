package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Payment;
import com.example.myShop.domain.exception.PaymentNotFoundException;
import com.example.myShop.domain.mapper.PaymentMapper;
import com.example.myShop.repository.PaymentRepository;
import com.example.myShop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Payment get(Integer id){
        Payment result =  paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getOrders());
        return result;
    }

    @Override
    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    @Override
    public Payment create(Payment payment){
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment payment, Integer id) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> paymentMapper.merge(current, payment))
                .map(paymentRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id){
        paymentRepository.deleteById(id);
    }
}
