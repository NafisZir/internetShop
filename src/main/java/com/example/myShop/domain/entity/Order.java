package com.example.myShop.domain.entity;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Jacksonized
@NoArgsConstructor
@Table(name = "Ordering")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_ID")
    int id;
    @Column(name = "count")
    int count;
    @Column(name = "price")
    int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_ID")
    Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_ID")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_ID")
    Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_ID")
    Receiving receiving;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_ID")
    Payment payment;
}
