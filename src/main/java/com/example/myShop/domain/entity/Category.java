package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedGoodsExistsException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
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
@Table(name = "categories")
public class Category extends BaseEntity{
    private String name;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "category",
                orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Goods> goods = new ArrayList<>();

    @PreRemove
    public void beforeDelete(){
        if(!goods.isEmpty()){
            throw new LinkedGoodsExistsException(id, getClass().getName());
        }
    }
}
