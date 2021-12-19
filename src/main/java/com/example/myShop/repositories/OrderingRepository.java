package com.example.myShop.repositories;

import com.example.myShop.models.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderingRepository extends JpaRepository<Ordering, Integer> {
    List<Ordering> findByClientID(Integer id);
    List<Ordering> findByGoodsID(Integer id);
    List<Ordering> findByPayMethod(String id);
    List<Ordering> findByReceiveID(Integer id);
    List<Ordering> findByStatusName(String id);
}
