package com.example.myShop.service;

import com.example.myShop.domain.entity.Receiving;
import com.example.myShop.domain.exception.ReceivingNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ReceivingService {
    /**
     * Find receiving from DB by id
     * @param id receiving id
     * @return Receiving from DB
     * @throws ReceivingNotFoundException
     */
    Receiving get(Integer id);

    /**
     * Find receiving from DB by id and initialize
     * @param id receiving id
     * @return Receiving from DB
     * @throws ReceivingNotFoundException
     */
    Receiving getAndInitialize(Integer id);

    /**
     * Find receivings from DB and initialize each
     * @param pageable class for return page
     * @return Page that contains receivings
     */
    Page<Receiving> getAndInitializeAll(Pageable pageable);

    /**
     * Get data of receiving, create a new receiving using this data and save it to DB
     * @param receiving contains a new receiving data
     * @return Created receiving
     */
    Receiving create(Receiving receiving);

    /**
     * Update receiving using merge and save to DB
     * @param id ID of receiving
     * @param receiving contains new data to update
     * @return Updated receiving
     * @throws ReceivingNotFoundException
     */
    Receiving update(Receiving receiving, Integer id);

    /**
     * Remove receiving by id. It may complete only if any order doesn't contain this receiving
     * @param id ID of receiving
     */
    void delete(Integer id);
}
