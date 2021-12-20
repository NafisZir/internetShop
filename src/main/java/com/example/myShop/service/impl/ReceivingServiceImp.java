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
    public List<Receiving> getReceivings(){
        return receivingRepository.findAll();
    }

    @Override
    public Receiving get(Integer id){
        return receivingRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Receiving receiving){
        receivingRepository.save(receiving);
    }

    @Override
    public void update(Receiving receiving) { receivingRepository.save(receiving); }

    @Override
    public void delete(Integer id){
        List<Order> orderList = orderService.getOrdersByReceiveId(id);

        if(orderList.isEmpty()){
            receivingRepository.deleteById(id);
        }
    }
}
