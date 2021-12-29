package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Producer;
import com.example.myShop.domain.mapper.ProducerMapper;
import com.example.myShop.repository.ProducerRepository;
import com.example.myShop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class ProducerServiceImp implements ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    @Override
    public Producer get(Integer id) { return producerRepository.findById(id).orElse(null); }

    @Override
    public Producer create(Producer producer) { return producerRepository.save(producer); }

    @Override
    public Producer update(Producer producer, Integer id) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> producerMapper.merge(current,producer))
                .map(producerRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) { producerRepository.deleteById(id); }
}
