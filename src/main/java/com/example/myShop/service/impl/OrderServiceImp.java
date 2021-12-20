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
    private final Principal principal;

    @Override
    public Order get(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public String create(Order order, Integer goodId) {
        Goods good = goodsService.get(goodId);

        // count must be less or equal of good's availability
        int count = order.getCount();
        if(count > good.getAvailability()){
            return "Wrong count";
        }

        int price = goodsService.get(goodId).getPrice() * count;

        //Reduce availability for good
        good.decAvailability(count);
        goodsService.update(good);

        order.setGoodsID(goodId);
        order.setClientID(userService.getUserByPrincipal(principal).getId());
        order.setPrice(price);
        order.setStatus(statusService.getPrimaryStatus().getStatus());
        orderRepository.save(order);

        return "successfully";
    }

    @Override
    public void update(Order order) {
        orderRepository.save(order);
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
    public List<Order> getOrdersByGoodsID(Integer id) {
        return orderRepository.findByGoodsID(id);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByPayMethod(String id) {
        return orderRepository.findByPayMethod(id);
    }

    @Override
    public List<Order> getOrdersByReceiveId(Integer id) {
        return orderRepository.findByReceiveID(id);
    }

    @Override
    public List<Order> getOrdersByStatusName(String id) {
        return orderRepository.findByStatus(id);
    }
}
