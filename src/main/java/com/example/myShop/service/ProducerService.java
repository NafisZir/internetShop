package com.example.myShop.service;

import com.example.myShop.domain.entity.Producer;
import com.example.myShop.domain.exception.ProducerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface ProducerService {
    /**
     * Find producer from DB by id
     * @param id producer id
     * @return Producer from DB
     * @throws ProducerNotFoundException
     */
    Producer get(Integer id);

    /**
     * Find producer from DB by id and initialize
     * @param id producer id
     * @return Producer from DB
     * @throws ProducerNotFoundException
     */
    Producer getAndInitialize(Integer id);

    /**
     * Find producers from DB and initialize each
     * @param pageable class for return page
     * @return Page that contains producers
     */
    Page<Producer> getAndInitializeAll(Pageable pageable);

    /**
     * Get data of producer, create a new producer using this data and save it to DB
     * @param producer contains a new producer data
     * @return Created producer
     */
    Producer create(Producer producer);

    /**
     * Update producer using merge and save to DB
     * @param id ID of producer
     * @param producer contains new data to update
     * @return Updated producer
     * @throws ProducerNotFoundException
     */
    Producer update(Producer producer, Integer id);

    /**
     * Remove producer by id. It may complete only if producer doesn't contain goods
     * @param id ID of producer
     */
    void delete(Integer id);
}
