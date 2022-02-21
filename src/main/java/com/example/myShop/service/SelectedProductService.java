package com.example.myShop.service;

import com.example.myShop.domain.entity.SelectedProduct;
import com.example.myShop.domain.exception.SelectedProductChangeException;
import com.example.myShop.domain.exception.SelectedProductCheckCountException;
import com.example.myShop.domain.exception.SelectedProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nafis
 * @since 09.02.2022
 */

public interface SelectedProductService {
    /**
     * Find selected product from DB by id
     * @param id selected product id
     * @return selected product from DB
     * @throws SelectedProductNotFoundException
     */
    SelectedProduct get(Integer id);

    /**
     * Find selected product from DB by id and initialize
     * @param id selected product id
     * @return selected product from DB
     * @throws SelectedProductNotFoundException
     */
    SelectedProduct getAndInitialize(Integer id);

    /**
     * Find selected products from DB by order id and initialize each
     * @param pageable class for return page
     * @param orderId order id
     * @return Page that contains selected products
     */
    Page<SelectedProduct> getAndInitializeAll(Pageable pageable, Integer orderId);

    /**
     * Save a new selected product to DB.
     *
     * If user doesn't contain order with OrderStatus.CREATING there is creating a new order. Else method sets to
     * selected product existed order with OrderStatus.CREATING. Therefore, one user cannot have two orders
     * with OrderStatus.CREATING.
     *
     * Price compute dynamical with multiply count and goods price.
     * If selectedProduct.count more than goods count throws SelectedProductCheckCountException. When added
     * a new selected product to existed order, total price in order refreshed.
     *
     * @param selectedProduct contains a new selected product data
     * @param goodsId goods id that is contained in selected product
     * @param userId user id who select this selected product
     * @return Created selected product
     * @throws SelectedProductCheckCountException count in selected product more than count in goods
     */
    SelectedProduct create(SelectedProduct selectedProduct, Integer goodsId, Integer userId);

    /**
     * Update selected product.
     *
     * Before updating method checks order status. If order status isn't CREATING throws SelectedProductChangeException.
     * Because can't be changed a selected product in an active, cancelled or completed order.
     *
     * During updating recompute price and refresh total price in order
     *
     * @param id ID of selected product
     * @param selectedProduct contains new data to update
     * @return Updated selected product
     * @throws SelectedProductNotFoundException
     * @throws SelectedProductChangeException order status isn't CREATING
     */
    SelectedProduct update(Integer id, SelectedProduct selectedProduct);

    /**
     * Remove selected product by id. It may complete only if order status is CREATING.
     * If after removing order will not have any selected product, order also is removed.
     * Else just refreshed total price in order.
     * @param id ID of selected product
     */
    void delete(Integer id);
}
