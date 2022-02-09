package com.example.myShop.service;

import com.example.myShop.domain.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface UserService {
    User getAndInitialize(Integer id);

    User create(User userJson);

    User update(User userJson, Integer id);

    void delete(Integer id);

    Map<String, Object> getAndInitializeAll(int page, int size);
}
