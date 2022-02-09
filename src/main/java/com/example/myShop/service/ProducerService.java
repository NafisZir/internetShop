package com.example.myShop.service;

import com.example.myShop.domain.entity.Producer;

import java.util.Map;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ProducerService {
    Producer getAndInitialize(Integer id);

    Map<String, Object> getAndInitializeAll(int page, int size);

    Producer create(Producer producer);

    Producer update(Producer producer, Integer id);

    void delete(Integer id);
}
