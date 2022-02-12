package com.example.myShop.service;

import com.example.myShop.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface CategoryService {
    Category get(Integer id);

    Category getAndInitialize(Integer id);

    Page<Category> getAndInitializeAll(Pageable pageable);

    Category create(Category categoryJson);

    Category update(Integer id, Category categoryJson);

    void delete(Integer id);
}
