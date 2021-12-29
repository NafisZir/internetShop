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
@Table(name = "payments")
public class Payment extends BaseEntity{
    @Column(name = "pay_method")
    private String payMethod;

    @OneToMany(mappedBy = "payment")
    private List<Order> orders = new ArrayList<>();

    @PreRemove
    public void beforeDelete(){
        if(!orders.isEmpty()){
            throw new LinkedOrdersExistsException(id, this.getClass().getName());
        }
    }
}
