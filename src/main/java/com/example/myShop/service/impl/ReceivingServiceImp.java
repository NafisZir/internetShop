package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.Receiving;
import com.example.myShop.repository.ReceivingRepository;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.ReceivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class ReceivingServiceImp implements ReceivingService {
    private final ReceivingRepository receivingRepository;
    private final OrderService orderService;

    @Override
    public Receiving get(Integer id){
        return receivingRepository.findById(id).orElse(null);
    }

    @Override
    public Receiving create(Receiving receiving) {
        return receivingRepository.save(receiving);
    }

    @Override
    public Receiving update(Receiving receiving, Integer id){
        receiving.setId(id);
        return receivingRepository.save(receiving);
    }

    @Override
    public void delete(Integer id){
        List<Order> orderList = orderService.getOrdersByReceiveId(id);

        if(orderList.isEmpty()){
            receivingRepository.deleteById(id);
        }
    }
}
