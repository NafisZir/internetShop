package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedOrdersExistsException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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
@Table(name = "goods")
public class Goods extends BaseEntity{
    private String name;
    private BigDecimal price;
    @Column(name = "availability")
    private Long count;
    private String imageUrl;

    @JsonIgnore
    @Setter(PRIVATE)
    @OneToMany(mappedBy = "goods",
               orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<SelectedProduct> selectedProducts = new ArrayList<>();

    @JsonIgnore
    @JoinColumn(name = "producer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producer producer;

    @JsonIgnore
    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @PreRemove
    public void beforeDelete(){
        if(!selectedProducts.isEmpty()){
            throw new LinkedOrdersExistsException(this.id, this.getClass().getName());
        }
    }

    public void decCount(Long orderCount){
        count -= orderCount;
    }
}
