package com.example.myShop.repository;

import com.example.myShop.domain.entity.BankCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nafis
 * @since 09.02.2022
 */
public interface BankCardRepository extends JpaRepository<BankCard, Integer> {
    Page<BankCard> findAllByUserId(Pageable pageable, Integer userId);
}
