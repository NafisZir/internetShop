package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedGoodsExistsException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;
import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Table(name = "producers")
public class Producer extends BaseEntity{
    @Column(name = "producer_name")
    private String name;
    private String country;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "producer",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Goods> goods = new ArrayList<>();

    @PreRemove
    public void beforeDelete(){
        if(!goods.isEmpty()){
            throw new LinkedGoodsExistsException(name, this.getClass().getName());
        }
    }
}
