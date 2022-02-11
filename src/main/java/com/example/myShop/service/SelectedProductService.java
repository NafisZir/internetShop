package com.example.myShop.service;

import com.example.myShop.domain.entity.SelectedProduct;

import java.util.Map;

/**
 * @author nafis
 * @since 09.02.2022
 */

public interface SelectedProductService {
    SelectedProduct getAndInitialize(Integer id);

    Map<String, Object> getAndInitializeAll(Integer orderId, int page, int size);

    SelectedProduct create(SelectedProduct selectedProduct, Integer goodsId, Integer userId);

    SelectedProduct update(Integer id, SelectedProduct selectedProduct);

    void delete(Integer id);
}
