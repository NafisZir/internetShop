package com.example.myShop.repositories;

import com.example.myShop.models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    List<Goods> findByName(String name);
    List<Goods> findByCategoryID(Integer id);
}
