package com.example.myShop.service;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.domain.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface OrderService {
    /**
     * Find order from DB by id
     * @param id order id
     * @return order from DB
     * @throws OrderNotFoundException
     */
    Order get(Integer id);

    /**
     * Find order from DB by id and initialize
     * @param id order id
     * @return order from DB
     * @throws OrderNotFoundException
     */
    Order getAndInitialize(Integer id);

    /**
     * Find order from DB by user id and OrderStatus
     * @param orderStatus order status
     * @param userId user id
     * @return order from DB
     */
    Order getByUserAndOrderStatus(Integer userId, OrderStatus orderStatus);

    /**
     * Find orders from DB by user id and initialize each
     * @param pageable class for return page
     * @param userId user id
     * @return Page that contains orders
     */
    Page<Order> getAllByUserIdAndInit(Integer userId, Pageable pageable);

    /**
     * Find orders from DB by receiving id and initialize each
     * @param pageable class for return page
     * @param receivingId receiving id
     * @return Page that contains orders
     */
    Page<Order> getAllByReceivingIdAndInit(Integer receivingId, Pageable pageable);

    /**
     * Created a new order with OrderStatus.CREATING and BillStatus.AWAITING_PAYMENT.
     * @param price price of new selected product. It's set to total price
     * @param userId user id
     * @return Created order
     */
    Order create(BigDecimal price, Integer userId);

    /**
     * Checks count from selected product and from goods.
     * @param count count of goods in selected product
     * @param goods goods in selected product
     * @throws SelectedProductCheckCountException count in selected product more than count in goods
     */
    void checkCount(Long count, Goods goods);

    /**
     * Update order after checking several fields.
     *
     * Receiving and PaymentType may update if only OrderStatus is CREATING.
     *
     * If OrderStatus be willing change to PENDING, will be decreasing count from goods. Order doesn't may change
     * to PENDING before Receiving and PaymentType aren't initializing and if PaymentType is BANK_CARD_ONLINE, before
     * BillStatus isn't COMPLETED.
     * Order doesn't may change to COMPLETED, before BillStatus isn't COMPLETED.
     * If OrderStatus be willing change to CANCELLED, will be returning count to goods.
     *
     * Every bill status and order status have own place. If their order be willing not to save,
     * throws IllegalBillStatusException or IllegalOrderStatusException
     *
     * @param id ID of order
     * @param order contains new data to update
     * @param receivingId receiving id
     * @return Updated order
     * @throws OrderNotFoundException
     * @throws IllegalOrderUpdateException Receiving and PaymentType may update if only OrderStatus is CREATING.
     * @throws RequiredArgsException order with OrderStatus.PENDING have not  Receiving or PaymentType
     * @throws OrderStatusWontBeCompletedException BillStatus isn't COMPLETED, therefore, order status won't be COMPLETED
     * @throws BillStatusMustCompletedException order status cannot be COMPLETED, because bill status isn't COMPLETED
     * @throws IllegalOrderOfBillStatusException incorrect new bill status after current bill status. Save their order
     * @throws IllegalOrderOfOrderStatusException incorrect new order status after current order status. Save their order
     */
    Order update(Integer id, Order order, Integer receivingId);

    /**
     * Refresh total price of an order. Find difference between oldPrice and newPrice and add it to total price.
     * @param oldPrice price is kept in DB
     * @param newPrice refreshed price
     * @param order updated order
     */
    void refreshTotalPrice(BigDecimal oldPrice, BigDecimal newPrice, Order order);

    /**
     * Remove order by id. It may complete only if order isn't active
     * @param id ID of order
     * @throws OrderDeleteException Order is active
     */
    void delete(Integer id);
}
