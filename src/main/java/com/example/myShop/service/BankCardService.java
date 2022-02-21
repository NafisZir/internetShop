package com.example.myShop.service;

import com.example.myShop.domain.entity.BankCard;
import com.example.myShop.domain.exception.BankCardNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author nafis
 * @since 09.02.2022
 */
public interface BankCardService {
    /**
     * Find bank card from DB by id
     * @param id bank card id
     * @return Bank card from DB
     * @throws BankCardNotFoundException
     */
    BankCard get(Integer id);

    /**
     * Find bank card from DB by id and initialize
     * @param id bank card id
     * @return Bank card from DB
     * @throws BankCardNotFoundException
     */
    BankCard getAndInitialize(Integer id);

    /**
     * Find bank cards from DB by user id and initialize each
     * @param pageable class for return page
     * @param userId ID of user
     * @return Page that contains several bank cards
     */
    Page<BankCard> getAndInitializeAll(Pageable pageable, Integer userId);

    /**
     * Get data of bank card, create a new bank card using this data and save it to DB
     * @param bankCard contains a new bank card data
     * @param userId ID of user
     * @return Created bank card
     */
    BankCard create(BankCard bankCard, Integer userId);

    /**
     * Update bank card using merge and save to DB
     * @param id ID of bank card
     * @param bankCard contains new data to update
     * @return Updated bank card
     * @throws BankCardNotFoundException
     */
    BankCard update(Integer id, BankCard bankCard);

    /**
     * Remove bank card by id
     * @param id ID of bank card
     */
    void delete(Integer id);
}
