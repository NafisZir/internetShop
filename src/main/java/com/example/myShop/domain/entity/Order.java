package com.example.myShop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order extends BaseEntity{
    private BigDecimal price;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Column(name = "bill_status")
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus;

    @JsonIgnore
    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @JoinColumn(name = "receive_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Receiving receiving;

    @JsonIgnore
    @OneToMany(mappedBy = "order",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<SelectedProduct> selectedProducts = new ArrayList<>();

    public boolean isOrderActive(){
        return !orderStatus.equals(OrderStatus.CREATING) &&
                !orderStatus.equals(OrderStatus.CANCELLED) &&
                !orderStatus.equals(OrderStatus.COMPLETED);
    }

    public void addPrice(BigDecimal price){
        this.price = this.price.add(price);
    }
}
