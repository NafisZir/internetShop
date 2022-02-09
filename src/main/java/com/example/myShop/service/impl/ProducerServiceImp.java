package com.example.myShop.service.impl;

import com.example.myShop.domain.dto.producer.ProducerDto;
import com.example.myShop.domain.entity.Producer;
import com.example.myShop.domain.exception.ProducerNotFoundException;
import com.example.myShop.domain.mapper.ProducerMapper;
import com.example.myShop.repository.ProducerRepository;
import com.example.myShop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ProducerServiceImp implements ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    @Override
    public Producer getAndInitialize(Integer id) {
        Producer result =  producerRepository.findById(id).orElseThrow(() -> new ProducerNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getGoods());
        return result;
    }

    @Override
    public Map<String, Object> getAndInitializeAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Producer> producerPage = producerRepository.findAll(pageable);
        List<ProducerDto> listTemp = new ArrayList<>();

        for(Producer producer : producerPage){
            Hibernate.initialize(producer);
            Hibernate.initialize(producer.getGoods());
            listTemp.add(producerMapper.toDto(producer));
        }

        Page<ProducerDto> result = new PageImpl<>(listTemp);
        Map<String, Object> response = new HashMap<>();

        response.put("producers", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return response;
    }

    @Override
    public Producer create(Producer producer) { return producerRepository.save(producer); }

    @Override
    public Producer update(Producer producer, Integer id) {
        return Optional.of(id)
                .map(this::getAndInitialize)
                .map(current -> producerMapper.merge(current,producer))
                .map(producerRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) { producerRepository.deleteById(id); }
}
