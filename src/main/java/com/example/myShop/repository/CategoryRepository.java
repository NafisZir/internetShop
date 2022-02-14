package com.example.myShop.repository;

import com.example.myShop.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
