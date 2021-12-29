package com.example.myShop.service;

import com.example.myShop.domain.entity.Producer;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ProducerService {
    Producer get(Integer id);

    Producer create(Producer producer);

    Producer update(Producer producer, Integer id);

    void delete(Integer id);
}
