package com.example.myShop.service;

import com.example.myShop.domain.entity.SelectedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 09.02.2022
 */

public interface SelectedProductService {
    SelectedProduct get(Integer id);

    SelectedProduct getAndInitialize(Integer id);

    Page<SelectedProduct> getAndInitializeAll(Pageable pageable, Integer orderId);

    SelectedProduct create(SelectedProduct selectedProduct, Integer goodsId, Integer userId);

    SelectedProduct update(Integer id, SelectedProduct selectedProduct);

    void delete(Integer id);
}
