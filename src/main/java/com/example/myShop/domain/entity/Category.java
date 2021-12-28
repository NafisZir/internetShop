package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedGoodsExistsException;
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
@Table(name = "Category")
@AttributeOverride(name = "id", column = @Column(name = "category_ID"))
public class Category extends BaseEntity{
    @Column(name = "descr")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Goods> goods = new ArrayList<>();

    @PreRemove
    public void beforeDelete(){
        if(!goods.isEmpty()){
            throw new LinkedGoodsExistsException(id, getClass().getName());
        }
    }
}
