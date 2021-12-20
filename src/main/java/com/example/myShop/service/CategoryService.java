package com.example.myShop.service;

import com.example.myShop.domain.entity.Category;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface CategoryService {
    Category get(Integer id);

    void create(Category categoryJson);

    void update(Category categoryJson);

    void delete(Integer id);

    List<Category> getCategories();
}
