package com.example.myShop.service;

import com.example.myShop.domain.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface GoodsService {
    Goods get(Integer id);

    Goods getAndInitialize(Integer id);

    Page<Goods> getAndInitializeAll(Pageable pageable);

    Goods create(Goods goods, Integer categoryId, Integer producerId);

    Goods update(Integer id, Goods goodsJson);

    void delete(Integer id);
}
