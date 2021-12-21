package com.example.myShop.service;

import com.example.myShop.domain.entity.Status;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface StatusService {
    Integer getPrimaryStatusId();

    Status get(Integer id);

    Status create(Status status);

    Status update(Status status, Integer id);

    void delete(Integer id);
}
