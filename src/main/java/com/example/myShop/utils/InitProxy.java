package com.example.myShop.utils;

import com.example.myShop.domain.entity.*;
import org.hibernate.Hibernate;

/**
 * @author nafis
 * @since 14.02.2022
 */

public class InitProxy {
    public static Order initOrder(Order order){
        Hibernate.initialize(order);
        Hibernate.initialize(order.getUser());
        Hibernate.initialize(order.getReceiving());
        Hibernate.initialize(order.getSelectedProducts());
        return order;
    }

    public static SelectedProduct initSelectedProduct(SelectedProduct selectedProduct){
        Hibernate.initialize(selectedProduct);
        Hibernate.initialize(selectedProduct.getOrder());
        Hibernate.initialize(selectedProduct.getGoods());
        return selectedProduct;
    }

    public static Goods initGoods(Goods goods){
        Hibernate.initialize(goods);
        Hibernate.initialize(goods.getCategory());
        Hibernate.initialize(goods.getProducer());
        return goods;
    }

    public static User initUser(User user){
        Hibernate.initialize(user);
        Hibernate.initialize(user.getBankCards());
        return user;
    }

    public static BankCard initBankCard(BankCard bankCard){
        Hibernate.initialize(bankCard);
        Hibernate.initialize(bankCard.getUser());
        return bankCard;
    }

    public static Category initCategory(Category category){
        Hibernate.initialize(category);
        return category;
    }

    public static Producer initProducer(Producer producer){
        Hibernate.initialize(producer);
        return producer;
    }

    public static Receiving initReceiving(Receiving receiving){
        Hibernate.initialize(receiving);
        return receiving;
    }
}
