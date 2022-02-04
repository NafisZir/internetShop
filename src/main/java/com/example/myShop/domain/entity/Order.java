package com.example.myShop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order extends BaseEntity{
    private Integer count;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @JoinColumn(name = "goods_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;

    @JsonIgnore
    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @JoinColumn(name = "receive_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Receiving receiving;

    @JsonIgnore
    @JoinColumn(name = "pay_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;
}
