package com.example.myShop.service;

import com.example.myShop.domain.entity.User;
import com.example.myShop.domain.exception.AuthUserNotFoundException;
import com.example.myShop.domain.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface UserService {
    /**
     * Find user from DB by id
     * @param id user id
     * @return User from DB
     * @throws UserNotFoundException
     */
    User get(Integer id);

    /**
     * Find user from DB by email and initialize
     * @param email user email
     * @return user from DB
     * @throws AuthUserNotFoundException
     */
    User getByEmailAndInit(String email);

    /**
     * Find user from DB by id and initialize
     * @param id user id
     * @return user from DB
     * @throws UserNotFoundException
     */
    User getAndInitialize(Integer id);

    /**
     * Find users from DB and initialize each
     * @param pageable class for return page
     * @return Page that contains users
     */
    Page<User> getAndInitializeAll(Pageable pageable);

    /**
     * Get data of user, create a new user using this data and save it to DB
     * @param user contains a new user data
     * @return Created user
     */
    User create(User user);

    /**
     * Update user using merge and save to DB
     * @param id ID of user
     * @param user contains new data to update
     * @return Updated user
     * @throws UserNotFoundException
     */
    User update(User user, Integer id);

    /**
     * Remove user by id. It may complete only if user doesn't contain an active order
     * @param id ID of user
     */
    void delete(Integer id);
}
