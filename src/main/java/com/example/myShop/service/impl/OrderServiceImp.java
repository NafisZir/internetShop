package com.example.myShop.service.impl;

import com.example.myShop.domain.dto.order.OrderDto;
import com.example.myShop.domain.entity.BillStatus;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.OrderStatus;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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
    public Order getAndInitialize(Integer id) {
        Order result = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getUser());
        Hibernate.initialize(result.getReceiving());
        Hibernate.initialize(result.getSelectedProducts());
        return result;
    }

    public Map<String, Object> getAndInitializeAll(int page, int size, Integer userId){
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findAllByUserId(pageable, userId);
        List<OrderDto> listTemp = new ArrayList<>();

        for(Order order : orderPage){
            Hibernate.initialize(order);
            Hibernate.initialize(order.getUser());
            Hibernate.initialize(order.getReceiving());
            Hibernate.initialize(order.getSelectedProducts());

            listTemp.add(orderMapper.toDto(order));
        }

        Page<OrderDto> result = new PageImpl<>(listTemp);
        Map<String, Object> response = new HashMap<>();

        response.put("orders", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return response;
    }

    @Override
    public Order create(BigDecimal price, Integer userId) {
        Order order = new Order();
        order.setUser(userService.getAndInitialize(userId));
        order.setOrderStatus(OrderStatus.CREATING);
        order.setBillStatus(BillStatus.AWAITING_PAYMENT);
        order.setPrice(price);

        return orderRepository.save(order);
    }

    @Override
    public Order  update(Integer id, Order order) {
        return Optional.of(id)
                .map(this::getAndInitialize)
                .map(current -> orderMapper.merge(current, order))
                .map(orderRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        Order order = getAndInitialize(id);
        if(order.isOrderActive()){
            throw new OrderDeleteException("Delete operation is not acceptable for status: "
                    + order.getOrderStatus().getStatus() +
                    ". Status must be CREATING, CANCELED or COMPLETED. " +
                    "Order id: " + order.getId());
        }

        orderRepository.deleteById(id);
    }
}
