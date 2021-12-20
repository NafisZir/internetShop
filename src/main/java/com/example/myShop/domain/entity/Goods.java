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
    @Column(name = "producer_Name")
    String producerName;
    @Column(name = "category_ID")
    int categoryID;
    @Column(name = "availability")
    int availability;
    @Column(name = "image")
    String image;

    public void decAvailability(Integer count){
        availability -= count;
    }
}
