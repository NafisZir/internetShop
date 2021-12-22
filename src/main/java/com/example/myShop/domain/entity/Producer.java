package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedGoodsExistsException;
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
@Table(name = "Producer")
public class Producer {
    @Id
    @Column(name = "producer_Name")
    String name;
    @Column(name = "country")
    String country;

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
    List<Goods> goods;

    @PreRemove
    public void beforeDelete(){
        if(!goods.isEmpty()){
            throw new LinkedGoodsExistsException(this.name, this.getClass().getName());
        }
    }
}
