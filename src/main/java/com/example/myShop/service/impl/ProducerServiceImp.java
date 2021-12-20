package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Producer;
import com.example.myShop.repository.ProducerRepository;
import com.example.myShop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class ProducerServiceImp implements ProducerService {
    private final ProducerRepository producerRepository;

    @Override
    public Producer get(String name) { return producerRepository.findById(name).orElse(null); }

    @Override
    public List<Producer> getProducers(){
        return producerRepository.findAll();
    }

    @Override
    public void create(Producer producer) { producerRepository.save(producer); }

    @Override
    public void update(Producer producer) { producerRepository.save(producer); }

    @Override
    public void delete(String name) { producerRepository.deleteById(name); }
}
