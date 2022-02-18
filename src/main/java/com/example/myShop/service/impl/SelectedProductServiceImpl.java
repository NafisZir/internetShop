package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.SelectedProduct;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.domain.exception.SelectedProductChangeException;
import com.example.myShop.domain.exception.SelectedProductCheckCountException;
import com.example.myShop.domain.exception.SelectedProductNotFoundException;
import com.example.myShop.domain.mapper.SelectedProductMapper;
import com.example.myShop.repository.SelectedProductRepository;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.SelectedProductService;
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
 * @since 10.02.2022
 */

@Service
@Transactional
@RequiredArgsConstructor
public class SelectedProductServiceImpl implements SelectedProductService {
    private final SelectedProductRepository selectedProductRepository;
    private final SelectedProductMapper selectedProductMapper;
    private final GoodsService goodsService;
    private final OrderService orderService;

    @Override
    public SelectedProduct get(Integer id) {
        return selectedProductRepository.findById(id).orElseThrow(() -> new SelectedProductNotFoundException(id));
    }

    @Override
    public SelectedProduct getAndInitialize(Integer id) {
        return Optional.of(id)
                .map(selectedProductRepository::findById)
                .get()
                .map(InitProxy::initSelectedProduct)
                .orElseThrow(() -> new SelectedProductNotFoundException(id));
    }

    @Override
    public Page<SelectedProduct> getAndInitializeAll(Pageable pageable, Integer orderId) {
        Page<SelectedProduct> selectedProductPage = selectedProductRepository.findAllByOrderId(pageable, orderId);
        List<SelectedProduct> list = new ArrayList<>();

        for(SelectedProduct selectedProduct : selectedProductPage){
            list.add(InitProxy.initSelectedProduct(selectedProduct));
        }

        return new PageImpl<>(list);
    }

    private BigDecimal computePrice(Goods goods, Long count){
        orderService.checkCount(count, goods);

        BigDecimal countBigDec = new BigDecimal(count);
        return goods.getPrice().multiply(countBigDec);
    }

    private void checkOrderStatus(OrderStatus status){
        if(!status.equals(OrderStatus.CREATING)){
            throw new SelectedProductChangeException(status);
        }
    }

    @Override
    public SelectedProduct create(SelectedProduct selectedProduct, Integer goodsId, Integer userId) {
        Goods goods = goodsService.get(goodsId);

        BigDecimal price = computePrice(goods, selectedProduct.getCount());
        selectedProduct.setPrice(price);
        selectedProduct.setGoods(goods);

        Order order = orderService.getByUserAndOrderStatus(userId, OrderStatus.CREATING);
        if(order == null){
            order = orderService.create(price, userId);
        } else {
            BigDecimal oldPrice = new BigDecimal(0);
            orderService.refreshTotalPrice(oldPrice, price, order);
        }
        selectedProduct.setOrder(order);

        return selectedProductRepository.save(selectedProduct);
    }

    @Override
    public SelectedProduct update(Integer id, SelectedProduct selectedProduct) {
        SelectedProduct selProductFromDB = getAndInitialize(id);
        Order order = selProductFromDB.getOrder();

        checkOrderStatus(order.getOrderStatus());

        BigDecimal oldPrice = selProductFromDB.getPrice();
        BigDecimal newPrice = computePrice(selProductFromDB.getGoods(), selectedProduct.getCount());
        selectedProduct.setPrice(newPrice);
        orderService.refreshTotalPrice(oldPrice, newPrice, order);

        return Optional.of(selProductFromDB)
                .map(current -> selectedProductMapper.merge(current, selectedProduct))
                .map(selectedProductRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        SelectedProduct selectedProduct = get(id);
        Order order = selectedProduct.getOrder();

        checkOrderStatus(order.getOrderStatus());

        if (order.getSelectedProducts().size() == 1){
            orderService.delete(order.getId());
        } else {
            BigDecimal oldPrice = selectedProduct.getPrice();
            BigDecimal newPrice = new BigDecimal(0);
            orderService.refreshTotalPrice(oldPrice, newPrice, order);

            order.removeSelectedProduct(selectedProduct);
        }
    }
}
