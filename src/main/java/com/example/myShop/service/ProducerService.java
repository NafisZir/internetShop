package com.example.myShop.service;

import com.example.myShop.domain.entity.Producer;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ProducerService {
    Producer get(Integer id);

    List<Producer> getAll();

    Producer create(Producer producer);

    Producer update(Producer producer, Integer id);

    void delete(Integer id);
}
