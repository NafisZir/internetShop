package com.example.myShop.repository;

import com.example.myShop.domain.entity.SelectedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nafis
 * @since 09.02.2022
 */
public interface SelectedProductRepository extends JpaRepository<SelectedProduct, Integer> {
    Page<SelectedProduct> findAllByOrderId(Pageable pageable, Integer orderId);
}
