package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.Receiving;
import com.example.myShop.domain.entity.SelectedProduct;
import com.example.myShop.domain.enums.BillStatus;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.domain.enums.PaymentType;
import com.example.myShop.domain.exception.*;
import com.example.myShop.domain.mapper.OrderMapper;
import com.example.myShop.repository.OrderRepository;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.ReceivingService;
import com.example.myShop.service.UserService;
import com.example.myShop.utils.InitProxy;
import lombok.RequiredArgsConstructor;
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
    private final ReceivingService receivingService;
    private final OrderRepository orderRepository;
    private final GoodsService goodsService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @Override
    public Order get(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Order getAndInitialize(Integer id) {
        return Optional.of(id)
                .map(orderRepository::findById)
                .get()
                .map(InitProxy::initOrder)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Page<Order> getAllByUserIdAndInit(Integer userId, Pageable pageable){
        Page<Order> orderPage = orderRepository.findAllByUserId(pageable, userId);
        List<Order> list = new ArrayList<>();

        for(Order order : orderPage){
            list.add(InitProxy.initOrder(order));
        }

        return new PageImpl<>(list);
    }

    @Override
    public Page<Order> getAllByReceivingIdAndInit(Integer receivingId, Pageable pageable){
        Page<Order> orderPage = orderRepository.findAllByReceivingId(pageable, receivingId);
        List<Order> list = new ArrayList<>();

        for(Order order : orderPage){
            list.add(InitProxy.initOrder(order));
        }

        return new PageImpl<>(list);
    }

    @Override
    public Order getByUserAndOrderStatus(Integer userId, OrderStatus orderStatus){
        return orderRepository.findByUserIdAndOrderStatus(userId, orderStatus);
    }

    @Override
    public Order create(BigDecimal price, Integer userId) {
        Order order = new Order();
        order.setUser(userService.get(userId));
        order.setOrderStatus(OrderStatus.CREATING);
        order.setBillStatus(BillStatus.AWAITING_PAYMENT);
        order.setTotalPrice(price);

        return orderRepository.save(order);
    }

    @Override
    public void checkCount(Long count, Goods goods){
        long goodsCount = goods.getCount();

        if(count > goodsCount){
            throw new SelectedProductCheckCountException(count, goodsCount);
        }
    }

    @Override
    public Order  update(final Integer id, final Order order, Integer receivingId) {
        final Order orderFromDB = getAndInitialize(id);

        if(receivingId != null){
            checkOrderStatus(orderFromDB.getOrderStatus());
            order.setReceiving(receivingService.get(receivingId));
        }
        if(order.getOrderStatus() != null){
            processOrderStatus(order, orderFromDB);
        }
        if(order.getPaymentType()!= null){
            checkOrderStatus(orderFromDB.getOrderStatus());
            processPaymentTypes(order, orderFromDB);
        }
        if(order.getBillStatus() != null){
            processBillStatus(order, orderFromDB);
        }

        return Optional.of(orderFromDB)
                .map(current -> orderMapper.merge(current, order))
                .map(orderRepository::save)
                .orElseThrow();
    }

    private void processOrderStatus(Order order, Order orderFromDB){
        OrderStatus newOrderStatus = order.getOrderStatus();
        OrderStatus oldOrderStatus = orderFromDB.getOrderStatus();

        if(newOrderStatus == OrderStatus.CANCELLED) {
            returnGoodsCount(orderFromDB.getSelectedProducts());
        } else {
            checkOrderStatusNum(newOrderStatus, oldOrderStatus);

            if (newOrderStatus == OrderStatus.PENDING) {
                if (isPaymentTypeNotInit(order.getPaymentType(), orderFromDB.getPaymentType()) ||
                        isReceivingNotInit(order.getReceiving(), orderFromDB.getReceiving())) {
                    throw new RequiredArgsException(oldOrderStatus, newOrderStatus);
                }
                processPaymentTypes(order, orderFromDB);
                decGoodsCount(orderFromDB.getSelectedProducts());
            }

            if(newOrderStatus == OrderStatus.COMPLETED){
                if(isBillStatusNotCompleted(order.getBillStatus(), orderFromDB.getBillStatus())){
                    throw new OrderStatusWontBeCompletedException();
                }
            }
        }
    }

    private void processPaymentTypes(Order order, Order orderFromDB){
        if(order.getPaymentType() == PaymentType.BANK_CARD_ONLINE){
            if(isBillStatusNotCompleted(order.getBillStatus(), orderFromDB.getBillStatus())){
                throw new BillStatusMustCompletedException();
            }
        }
    }

    private void checkOrderStatus(OrderStatus status){
        if(status != OrderStatus.CREATING){
            throw new IllegalOrderUpdateException();
        }
    }

    private void processBillStatus(Order order, Order orderFromDB){
        checkBillStatusNum(order.getBillStatus(), orderFromDB.getBillStatus());
    }

    private void decGoodsCount(List<SelectedProduct> selectedProducts){
        for(SelectedProduct selProduct : selectedProducts){
            final Long count = selProduct.getCount();
            Goods goods = goodsService.get(selProduct
                    .getGoods()
                    .getId());
            checkCount(count, goods);
            goods.decCount(count);
        }
    }

    private void returnGoodsCount(List<SelectedProduct> selectedProducts){
        for(SelectedProduct selProduct : selectedProducts){
            final Long count = selProduct.getCount();
            Goods goods = goodsService.get(selProduct
                    .getGoods()
                    .getId());
            goods.incCount(count);
        }
    }

    private void checkOrderStatusNum(OrderStatus newStatus, OrderStatus oldStatus){
        int differenceNumber =  newStatus.getNumber() - oldStatus.getNumber();

        if(differenceNumber != 1){
            throw new IllegalOrderOfOrderStatusException(newStatus, oldStatus);
        }
    }

    private boolean isBillStatusNotCompleted(BillStatus newStatus, BillStatus oldStatus){
        BillStatus completed = BillStatus.COMPLETED;
        return (oldStatus != completed) && (newStatus != completed);
    }

    private boolean isPaymentTypeNotInit(PaymentType newType, PaymentType oldType){
        return (oldType == null) && (newType == null);
    }

    private boolean isReceivingNotInit(Receiving newReceiving, Receiving oldReceiving){
        return (oldReceiving == null) && (newReceiving == null);
    }

    private void checkBillStatusNum(BillStatus newStatus, BillStatus oldStatus){
        int differenceNumber =  newStatus.getNumber() - oldStatus.getNumber();

         if(differenceNumber != 1) {
             throw new IllegalOrderOfBillStatusException(newStatus, oldStatus);
         }
    }

    @Override
    public void refreshTotalPrice(BigDecimal oldPrice, BigDecimal newPrice, Order order){
        BigDecimal differenceOfPrice = newPrice.subtract(oldPrice);
        order.addPrice(differenceOfPrice);

        orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        Order order = get(id);
        if(order.isOrderActive()){
            throw new OrderDeleteException(order.getOrderStatus(), order.getId());
        }

        orderRepository.delete(order);
    }
}
