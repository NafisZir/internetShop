package com.example.myShop.service;

import com.example.myShop.domain.entity.BankCard;

import java.util.Map;

/**
 * @author nafis
 * @since 09.02.2022
 */
public interface BankCardService {
    BankCard getAndInitialize(Integer id);

    Map<String, Object> getAndInitializeAll(int page, int size, Integer userId);

    BankCard create(BankCard bankCard, Integer userId);

    BankCard update(Integer id, BankCard bankCard);

    void delete(Integer id);
}
