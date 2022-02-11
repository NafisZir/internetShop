package com.example.myShop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author nafis
 * @since 09.02.2022
 */

@Entity
@Setter
@Getter
@Table(name = "selected_products")
public class SelectedProduct extends BaseEntity{
    private Integer count;
    private BigDecimal price;

    @JsonIgnore
    @JoinColumn(name = "goods_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;

    @JsonIgnore
    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
