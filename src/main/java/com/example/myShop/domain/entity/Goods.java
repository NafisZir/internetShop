package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedOrdersExistsException;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Table(name = "Goods")
@AttributeOverride(name = "id", column = @Column(name = "goods_ID"))
public class Goods extends BaseEntity{
    private int id;
    private String name;
    private int price;
    private int availability;
    @Column(name = "image")
    private String imageUrl;

    @OneToMany(mappedBy = "goods")
    private List<Order> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_Name")
    private Producer producer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_ID")
    private Category category;

    @PreRemove
    public void beforeDelete(){
        if(!orders.isEmpty()){
            throw new LinkedOrdersExistsException(this.id, this.getClass().getName());
        }
    }

    public void decAvailability(Integer count){
        availability -= count;
    }
}
