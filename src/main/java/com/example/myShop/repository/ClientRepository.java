package com.example.myShop.repository;

import com.example.myShop.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface ClientRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
