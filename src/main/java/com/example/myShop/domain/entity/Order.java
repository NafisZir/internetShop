package com.example.myShop.domain.entity;

import com.example.myShop.domain.enums.BillStatus;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.domain.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static javax.persistence.CascadeType.*;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order extends BaseEntity{
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "bill_status")
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus;

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "receive_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Receiving receiving;

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
        totalPrice = totalPrice.add(price);
    }

    public void removeSelectedProduct(SelectedProduct selectedProduct){
        this.selectedProducts.remove(selectedProduct);
    }
}
