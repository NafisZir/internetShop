package com.example.myShop.service;

import com.example.myShop.domain.entity.Category;
import com.example.myShop.domain.exception.CategoryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface CategoryService {
    /**
     * Find category from DB by id
     * @param id category id
     * @return Category from DB
     * @throws CategoryNotFoundException
     */
    Category get(Integer id);

    /**
     * Find category from DB by id and initialize
     * @param id category id
     * @return Category from DB
     * @throws CategoryNotFoundException
     */
    Category getAndInitialize(Integer id);

    /**
     * Find categories from DB and initialize each
     * @param pageable class for return page
     * @return Page that contains categories
     */
    Page<Category> getAndInitializeAll(Pageable pageable);

    /**
     * Get data of category, create a new category using this data and save it to DB
     * @param category contains a new category data
     * @return Created category
     */
    Category create(Category category);

    /**
     * Update category using merge and save to DB
     * @param id ID of category
     * @param category contains new data to update
     * @return Updated category
     * @throws CategoryNotFoundException
     */
    Category update(Integer id, Category category);

    /**
     * Remove category by id. It may complete only if category doesn't contain goods
     * @param id ID of category
     */
    void delete(Integer id);
}
