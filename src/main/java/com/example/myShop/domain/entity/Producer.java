package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedGoodsExistsException;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

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
@Table(name = "Producer")
public class Producer {
    @Id
    @Column(name = "producer_Name")
    private String name;

    private String country;

    @OneToMany(mappedBy = "producer")
    private List<Goods> goods = new ArrayList<>();

    @PreRemove
    public void beforeDelete(){
        if(!goods.isEmpty()){
            throw new LinkedGoodsExistsException(name, this.getClass().getName());
        }
    }
}
