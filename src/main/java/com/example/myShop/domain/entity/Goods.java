package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedOrdersExistsException;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Jacksonized
@NoArgsConstructor
@Table(name = "Goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_ID")
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "price")
    int price;
    @Column(name = "availability")
    int availability;
    @Column(name = "image")
    String image;

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    List<Order> orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_Name")
    Producer producer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_ID")
    Category category;

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
