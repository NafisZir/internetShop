package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.repository.OrderRepository;
import com.example.myShop.service.*;
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
    private final ReceivingService receivingService;
    private final PaymentService paymentService;

    @Override
    public Order get(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order create(Order order, Integer goodsId, Integer receiveId, Integer payId, Principal principal) {
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

        order.setGoods(goodsService.get(goodsId));
        order.setUser(userService.getUserByPrincipal(principal));
        order.setPrice(price);
        order.setStatus(statusService.get(statusService.getPrimaryStatusId()));     //!!!!!!!!!!!
        order.setReceiving(receivingService.get(receiveId));
        order.setPayment(paymentService.get(payId));
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
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByPayId(Integer id) {
        return orderRepository.findByPaymentId(id);
    }

    @Override
    public List<Order> getOrdersByReceiveId(Integer id) {
        return orderRepository.findByReceivingId(id);
    }
}
