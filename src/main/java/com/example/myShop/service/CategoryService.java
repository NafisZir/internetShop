package com.example.myShop.service;

import com.example.myShop.domain.entity.Category;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface CategoryService {
    Category get(Integer id);

    List<Category> getAll();

    Category create(Category categoryJson);

    Category update(Integer id, Category categoryJson);

    void delete(Integer id);
}
