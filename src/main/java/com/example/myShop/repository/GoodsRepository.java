package com.example.myShop.repository;

import com.example.myShop.domain.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    Page<Goods> findAllByCategoryId(Pageable pageable, Integer id);

    Page<Goods> findAllByProducerId(Pageable pageable, Integer id);
}
