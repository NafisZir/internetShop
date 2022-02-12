package com.example.myShop.service;

import com.example.myShop.domain.entity.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ProducerService {
    Producer get(Integer id);

    Producer getAndInitialize(Integer id);

    Page<Producer> getAndInitializeAll(Pageable pageable);

    Producer create(Producer producer);

    Producer update(Producer producer, Integer id);

    void delete(Integer id);
}
