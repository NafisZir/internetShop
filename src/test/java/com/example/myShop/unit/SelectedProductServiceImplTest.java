package com.example.myShop.unit;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.SelectedProduct;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.impl.SelectedProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

/**
 * @author nafis
 * @since 21.02.2022
 */

@ExtendWith(MockitoExtension.class)
public class SelectedProductServiceImplTest {
    @InjectMocks
    private SelectedProductServiceImpl selectedProductService;
    @Mock
    private GoodsService goodsService;
    @Mock
    private OrderService orderService;

    @Test
    public void testOrderNotFoundByUserAndOrderStatus(){
        SelectedProduct selectedProduct = new SelectedProduct();
        Goods goods = new Goods();

        Long count = 1L;
        Integer userId = 1;
        Integer goodsId = 1;
        OrderStatus orderStatus = OrderStatus.CREATING;
        BigDecimal goodsPrice = new BigDecimal(10);

        selectedProduct.setCount(count);
        goods.setCount(count);
        goods.setPrice(goodsPrice);

        Mockito.when(goodsService.get(goodsId)).thenReturn(goods);
        Mockito.when(orderService.getByUserAndOrderStatus(userId, orderStatus)).thenReturn(null);
        Mockito.when(orderService.create(goodsPrice, userId)).thenThrow(new RuntimeException("All is OK"));

        try {
            selectedProductService.create(selectedProduct, goodsId, userId);
        } catch (RuntimeException e){
            Assertions.assertEquals("All is OK", e.getMessage());
        }
    }

    @Test
    public void testOrderFoundByUserAndOrderStatus(){
        SelectedProduct selectedProduct = new SelectedProduct();
        Goods goods = new Goods();
        Order order = new Order();

        Long count = 1L;
        Integer userId = 1;
        Integer goodsId = 1;
        OrderStatus orderStatus = OrderStatus.CREATING;
        BigDecimal goodsPrice = new BigDecimal(10);
        BigDecimal oldPrice = new BigDecimal(0);

        selectedProduct.setCount(count);
        goods.setCount(count);
        goods.setPrice(goodsPrice);

        Mockito.when(goodsService.get(goodsId)).thenReturn(goods);
        Mockito.when(orderService.getByUserAndOrderStatus(userId, orderStatus)).thenReturn(order);
        Mockito.doThrow(new RuntimeException("All is OK")).when(orderService).refreshTotalPrice(oldPrice, goodsPrice, order);

        try {
            selectedProductService.create(selectedProduct, goodsId, userId);
        } catch (RuntimeException e){
            Assertions.assertEquals("All is OK", e.getMessage());
        }
    }
}
