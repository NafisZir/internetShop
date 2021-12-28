package com.example.myShop.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Table(name = "Ordering")
@AttributeOverride(name = "id", column = @Column(name = "order_ID"))
public class Order extends BaseEntity{
    private int count;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_ID")
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_ID")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_ID")
    private Receiving receiving;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_ID")
    private Payment payment;
}
