package com.example.myShop.repository;

import com.example.myShop.domain.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByStatus(String status);
}
