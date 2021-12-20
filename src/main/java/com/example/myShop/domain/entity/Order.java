package com.example.myShop.domain.entity;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Table(name = "Ordering")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_ID")
    int id;
    @Column(name = "goods_ID")
    int goodsID;
    @Column(name = "client_ID")
    int clientID;
    @Column(name = "status_Name")
    String status;
    @Column(name = "count")
    int count;
    @Column(name = "price")
    int price;
    @Column(name = "receive_ID")
    int receiveID;
    @Column(name = "pay_Method")
    String payMethod;
}
