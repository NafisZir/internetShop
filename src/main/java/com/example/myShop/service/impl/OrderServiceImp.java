package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.Status;
import com.example.myShop.domain.exception.OrderCheckCountException;
import com.example.myShop.domain.exception.OrderNotFoundException;
import com.example.myShop.domain.mapper.OrderMapper;
import com.example.myShop.repository.OrderRepository;
import com.example.myShop.service.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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
    private final GoodsService goodsService;
    private final UserService userService;
    private final ReceivingService receivingService;
    private final PaymentService paymentService;
    private final OrderMapper orderMapper;

    @Override
    public Order get(Integer id) {
        Order result = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getPayment());
        Hibernate.initialize(result.getReceiving());
        Hibernate.initialize(result.getGoods());
        Hibernate.initialize(result.getUser());
        return result;
    }

    public Map<String, Object> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        Page orderPage = orderRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();

        response.put("orders", orderPage.getContent());
        response.put("currentPage", orderPage.getNumber());
        response.put("totalItems", orderPage.getTotalElements());
        response.put("totalPages", orderPage.getTotalPages());

        return response;
    }

    private boolean checkCount(int count, int goodsId){
        Goods goods = goodsService.get(goodsId);
        long availability = goods.getCount();

        return count <= availability;
    }

    private void setPrice(Order order, Integer goodsId){
        Goods goods = goodsService.get(goodsId);
        int count = order.getCount();

        //Reduce availability for goods
        goods.decAvailability((long) count);
        goodsService.update(goodsId, goods);

        //Multiply count and price
        BigDecimal countBD = new BigDecimal(count);
        BigDecimal orderPrice = goods.getPrice().multiply(countBD);

        order.setPrice(orderPrice);
    }

    @Override
    public Order create(Order order, Integer goodsId, Integer receiveId, Integer payId, Integer userId) {
        order.setUser(userService.get(userId));

        if(!checkCount(order.getCount(), goodsId)){
            throw new OrderCheckCountException(goodsId);
        }

        setPrice(order, goodsId);
        order.setGoods(goodsService.get(goodsId));
        order.setStatus(Status.PENDING);
        order.setReceiving(receivingService.get(receiveId));
        order.setPayment(paymentService.get(payId));

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
        orderRepository.deleteById(id);
    }
}
