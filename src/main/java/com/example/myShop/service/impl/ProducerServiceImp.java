package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Producer;
import com.example.myShop.domain.exception.ProducerNotFoundException;
import com.example.myShop.domain.mapper.ProducerMapper;
import com.example.myShop.repository.ProducerRepository;
import com.example.myShop.service.ProducerService;
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
public class ProducerServiceImp implements ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    @Override
    public Producer get(Integer id) {
        return producerRepository.findById(id).orElseThrow(() -> new ProducerNotFoundException(id));
    }

    @Override
    public Producer getAndInitialize(Integer id) {
        return Optional.of(id)
                .map(producerRepository::findById)
                .get()
                .map(InitProxy::initProducer)
                .orElseThrow(() -> new ProducerNotFoundException(id));
    }

    @Override
    public Page<Producer> getAndInitializeAll(Pageable pageable){
        Page<Producer> producerPage = producerRepository.findAll(pageable);
        List<Producer> list = new ArrayList<>();

        for(Producer producer : producerPage){
            list.add(InitProxy.initProducer(producer));
        }

        return new PageImpl<>(list);
    }

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
