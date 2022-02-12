package com.example.myShop.service;

import com.example.myShop.domain.entity.BankCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author nafis
 * @since 09.02.2022
 */
public interface BankCardService {
    BankCard get(Integer id);

    BankCard getAndInitialize(Integer id);

    Page<BankCard> getAndInitializeAll(Pageable pageable, Integer userId);

    BankCard create(BankCard bankCard, Integer userId);

    BankCard update(Integer id, BankCard bankCard);

    void delete(Integer id);
}
