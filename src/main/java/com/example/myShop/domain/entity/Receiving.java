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
@Table(name = "receivings")
public class Receiving extends BaseEntity{
    @Column(name = "receive_method")
    private String receiveMethod;

    private String address;

    @OneToMany(mappedBy = "receiving")
    private List<Order> orders = new ArrayList<>();

    @PreRemove
    public void beforeDelete(){
        if(!orders.isEmpty()){
            throw new LinkedOrdersExistsException(id, this.getClass().getName());
        }
    }
}
