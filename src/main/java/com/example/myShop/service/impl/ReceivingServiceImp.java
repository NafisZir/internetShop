package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Receiving;
import com.example.myShop.domain.exception.ReceivingNotFoundException;
import com.example.myShop.domain.mapper.ReceivingMapper;
import com.example.myShop.repository.ReceivingRepository;
import com.example.myShop.service.ReceivingService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ReceivingServiceImp implements ReceivingService {
    private final ReceivingRepository receivingRepository;
    private final ReceivingMapper receivingMapper;

    @Override
    public Receiving get(Integer id){
        Receiving result = receivingRepository.findById(id).orElseThrow(() -> new ReceivingNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getOrders());
        return result;
    }

    @Override
    public Map<String, Object> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Receiving> receivingPage = receivingRepository.findAll(pageable);

        for(Receiving receiving : receivingPage){
            Hibernate.initialize(receiving);
            Hibernate.initialize(receiving.getOrders());
        }

        Map<String, Object> response = new HashMap<>();

        response.put("receivings", receivingPage.getContent());
        response.put("currentPage", receivingPage.getNumber());
        response.put("totalItems", receivingPage.getTotalElements());
        response.put("totalPages", receivingPage.getTotalPages());

        return response;
    }

    @Override
    public Receiving create(Receiving receiving) {
        return receivingRepository.save(receiving);
    }

    @Override
    public Receiving update(Receiving receiving, Integer id){
        return Optional.of(id)
                .map(this::get)
                .map(current -> receivingMapper.merge(current, receiving))
                .map(receivingRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id){
        receivingRepository.deleteById(id);
    }
}
