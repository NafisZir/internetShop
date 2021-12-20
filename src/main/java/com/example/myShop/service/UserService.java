package com.example.myShop.service;

import com.example.myShop.domain.entity.User;

import java.security.Principal;
import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface UserService {
    User get(Integer id);

    boolean create(User userJson);

    void update(User userJson);

    void delete(Integer id);

    List<User> getUsers();

    User getUserByPrincipal(Principal principal);
}
