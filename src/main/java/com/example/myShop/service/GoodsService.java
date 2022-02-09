package com.example.myShop.service;

import com.example.myShop.domain.entity.Goods;

import java.util.Map;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface GoodsService {
    Goods getAndInitialize(Integer id);

    Goods create(Goods goods, Integer categoryId, Integer producerId);

    Goods update(Integer id, Goods goodsJson);

    void delete(Integer id);

    Map<String, Object> getAndInitializeAll(int page, int size);
}
