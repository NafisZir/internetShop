package com.example.myShop.service;

import com.example.myShop.domain.entity.Receiving;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ReceivingService {
    List<Receiving> getReceivings();

    Receiving get(Integer id);

    void create(Receiving receiving);

    void update(Receiving receiving);

    void delete(Integer id);
}
