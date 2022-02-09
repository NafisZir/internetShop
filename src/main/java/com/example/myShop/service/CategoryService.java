package com.example.myShop.service;

import com.example.myShop.domain.entity.Category;

import java.util.Map;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface CategoryService {
    Category getAndInitialize(Integer id);

    Map<String, Object> getAndInitializeAll(int page, int size);

    Category create(Category categoryJson);

    Category update(Integer id, Category categoryJson);

    void delete(Integer id);
}
