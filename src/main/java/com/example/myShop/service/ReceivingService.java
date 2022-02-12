package com.example.myShop.service;

import com.example.myShop.domain.entity.Receiving;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ReceivingService {
    Receiving get(Integer id);

    Receiving getAndInitialize(Integer id);

    Page<Receiving> getAndInitializeAll(Pageable pageable);

    Receiving create(Receiving receiving);

    Receiving update(Receiving receiving, Integer id);

    void delete(Integer id);
}
