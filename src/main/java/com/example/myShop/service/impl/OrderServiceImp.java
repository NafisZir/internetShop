package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.repository.OrderRepository;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.StatusService;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{
    private final OrderRepository orderRepository;
    private final GoodsService goodsService;
    private final UserService userService;
    private final StatusService statusService;

    @Override
    public Order get(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order create(Order order, Integer goodsId, Principal principal) {
        Goods goods = goodsService.get(goodsId);

        // count must be less or equal of good's availability
        int count = order.getCount();
        if(count > goods.getAvailability()){
            return null;
        }

        int price = goodsService.get(goodsId).getPrice() * count;

        //Reduce availability for good
        goods.decAvailability(count);
        goodsService.update(goodsId, goods);

        order.setGoodsID(goodsId);
        order.setClientID(userService.getUserByPrincipal(principal).getId());
        order.setPrice(price);
        order.setStatusId(statusService.getPrimaryStatusId());
        return orderRepository.save(order);
    }

    @Override
    public Order  update(Integer id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrdersByClientID(Integer id) {
        return orderRepository.findByClientID(id);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByPayId(Integer id) {
        return orderRepository.findByPayId(id);
    }

    @Override
    public List<Order> getOrdersByReceiveId(Integer id) {
        return orderRepository.findByReceiveID(id);
    }
}
