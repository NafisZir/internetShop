package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Receiving;
import com.example.myShop.domain.exception.ReceivingNotFoundException;
import com.example.myShop.domain.mapper.ReceivingMapper;
import com.example.myShop.repository.ReceivingRepository;
import com.example.myShop.service.ReceivingService;
import com.example.myShop.utils.InitProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
        return receivingRepository.findById(id).orElseThrow(() -> new ReceivingNotFoundException(id));
    }

    @Override
    public Receiving getAndInitialize(Integer id){
        return Optional.of(id)
                .map(receivingRepository::findById)
                .get()
                .map(InitProxy::initReceiving)
                .orElseThrow(() -> new ReceivingNotFoundException(id));
    }

    @Override
    public Page<Receiving> getAndInitializeAll(Pageable pageable){
        Page<Receiving> receivingPage = receivingRepository.findAll(pageable);
        List<Receiving> list = new ArrayList<>();

        for(Receiving receiving : receivingPage){
            list.add(InitProxy.initReceiving(receiving));
        }

        return new PageImpl<>(list);
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
