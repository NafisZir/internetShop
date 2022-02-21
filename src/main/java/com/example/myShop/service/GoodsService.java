package com.example.myShop.service;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.exception.CategoryNotFoundException;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface GoodsService {
    /**
     * Find goods from DB by id
     * @param id goods id
     * @return Goods from DB
     * @throws GoodsNotFoundException
     */
    Goods get(Integer id);

    /**
     * Find goods from DB by id and initialize
     * @param id goods id
     * @return Goods from DB
     * @throws GoodsNotFoundException
     */
    Goods getAndInitialize(Integer id);

    /**
     * Find goods from DB and initialize each
     * @param pageable class for return page
     * @return Page that contains goods
     */
    Page<Goods> getAndInitializeAll(Pageable pageable);

    /**
     * Find goods from DB by category id and initialize each
     * @param pageable class for return page
     * @param categoryId category id
     * @return Page that contains goods
     */
    Page<Goods> getAllByCategoryIdAndInit(Pageable pageable, Integer categoryId);

    /**
     * Find goods from DB by producer id and initialize each
     * @param pageable class for return page
     * @param producerId producer id
     * @return Page that contains goods
     */
    Page<Goods> getAllByProducerIdAndInit(Pageable pageable, Integer producerId);

    /**
     * Get data of goods, create a new goods using this data and save it to DB.
     * To set producer and category fields method get their ID-s
     * @param goods contains a new goods data
     * @param categoryId category id
     * @param producerId producer id
     * @return Created goods
     */
    Goods create(Goods goods, Integer categoryId, Integer producerId);

    /**
     * Update goods using merge and save to DB
     * @param id ID of goods
     * @param goods contains new data to update
     * @return Updated goods
     * @throws GoodsNotFoundException
     */
    Goods update(Integer id, Goods goods);

    /**
     * Remove goods by id. It may complete only if any order doesn't contain this goods
     * @param id ID of goods
     */
    void delete(Integer id);
}
