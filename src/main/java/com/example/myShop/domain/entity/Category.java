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
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_ID")
    int id;
    @Column(name = "descr")
    String descr;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    List<Goods> goods;

    @PreRemove
    public void beforeDelete(){
        if(!goods.isEmpty()){
            throw new LinkedGoodsExistsException(this.id, this.getClass().getName());
        }
    }
}
