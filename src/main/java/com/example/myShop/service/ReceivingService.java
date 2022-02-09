package com.example.myShop.service;

import com.example.myShop.domain.entity.Receiving;

import java.util.Map;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ReceivingService {
    Receiving getAndInitialize(Integer id);

    Map<String, Object> getAndInitializeAll(int page, int size);

    Receiving create(Receiving receiving);

    Receiving update(Receiving receiving, Integer id);

    void delete(Integer id);
}
