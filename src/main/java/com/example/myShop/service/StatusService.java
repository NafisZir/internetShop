package com.example.myShop.service;

import com.example.myShop.domain.entity.Status;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface StatusService {
    Status getPrimaryStatus();

    List<Status> getStatuses();

    void create(Status status);

    void delete(String id);
}
