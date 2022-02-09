package com.example.myShop.domain.entity;

import com.example.myShop.domain.exception.LinkedOrdersExistsException;
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
@Table(name = "receivings")
public class Receiving extends BaseEntity{
    @Column(name = "receive_method")
    private String receiveMethod;

    private String address;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "receiving",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Order> orders = new ArrayList<>();

    @PreRemove
    public void beforeDelete(){
        if(!orders.isEmpty()){
            throw new LinkedOrdersExistsException(id, this.getClass().getName());
        }
    }
}
