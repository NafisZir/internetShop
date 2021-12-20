package com.example.myShop.service;

import com.example.myShop.domain.entity.Producer;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ProducerService {
    Producer get(String name);

    List<Producer> getProducers();

    void create(Producer producer);

    void update(Producer producer);

    void delete(String name);
}
