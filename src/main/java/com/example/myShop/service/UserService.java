package com.example.myShop.service;

import com.example.myShop.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface UserService {
    User get(Integer id);

    User getByEmailAndInit(String email);

    User getAndInitialize(Integer id);

    Page<User> getAndInitializeAll(Pageable pageable);

    User create(User userJson);

    User update(User userJson, Integer id);

    void delete(Integer id);
}
