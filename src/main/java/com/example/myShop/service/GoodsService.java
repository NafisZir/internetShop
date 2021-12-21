package com.example.myShop.service;

import com.example.myShop.domain.entity.Goods;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface GoodsService {
    Goods get(Integer id);

    Goods create(Goods goodsJson);

    Goods update(Integer id, Goods goodsJson);

    void delete(Integer id);

    List<Goods> getGoods(String name);

    List<Goods> getGoodsByCategory(Integer id);
}
