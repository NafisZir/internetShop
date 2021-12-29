package com.example.myShop.service;

import com.example.myShop.domain.entity.User;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface UserService {
    User get(Integer id);

    User create(User userJson);

    User update(User userJson, Integer id);

    void delete(Integer id);

    User getUserByEmail(String email);
}
