package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Receiving;
import com.example.myShop.repository.ReceivingRepository;
import com.example.myShop.service.ReceivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class ReceivingServiceImp implements ReceivingService {
    private final ReceivingRepository receivingRepository;

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
        receivingRepository.deleteById(id);
    }
}
