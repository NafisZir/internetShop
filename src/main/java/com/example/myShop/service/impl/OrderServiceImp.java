package com.example.myShop.service.impl;

import com.example.myShop.domain.enums.BillStatus;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.domain.exception.OrderDeleteException;
import com.example.myShop.domain.exception.OrderNotFoundException;
import com.example.myShop.domain.mapper.OrderMapper;
import com.example.myShop.repository.OrderRepository;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @Override
    public Order get(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Order getAndInitialize(Integer id) {
        Order result = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getUser());
        Hibernate.initialize(result.getReceiving());
        Hibernate.initialize(result.getSelectedProducts());
        return result;
    }

    @Override
    public Page<Order> getAndInitializeAll(Integer userId, Pageable pageable){
        Page<Order> orderPage = orderRepository.findAllByUserId(pageable, userId);
        List<Order> list = new ArrayList<>();

        for(Order order : orderPage){
            Hibernate.initialize(order);
            Hibernate.initialize(order.getUser());
            Hibernate.initialize(order.getReceiving());
            Hibernate.initialize(order.getSelectedProducts());

            list.add(order);
        }

        return new PageImpl<>(list);
    }

    @Override
    public Order create(BigDecimal price, Integer userId) {
        Order order = new Order();
        order.setUser(userService.get(userId));
        order.setOrderStatus(OrderStatus.CREATING);
        order.setBillStatus(BillStatus.AWAITING_PAYMENT);
        order.setPrice(price);

        return orderRepository.save(order);
    }

    @Override
    public Order  update(Integer id, Order order) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> orderMapper.merge(current, order))
                .map(orderRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        Order order = get(id);
        if(order.isOrderActive()){
            throw new OrderDeleteException("Delete operation is not acceptable for status: "
                    + order.getOrderStatus().getStatus() +
                    ". Status must be CREATING, CANCELED or COMPLETED. " +
                    "Order id: " + order.getId());
        }

        orderRepository.delete(order);
    }
}
