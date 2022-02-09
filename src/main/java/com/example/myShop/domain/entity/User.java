package com.example.myShop.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Table(name = "clients")
public class User extends BaseEntity{
    private String name;
    private String phone;
    private String email;
    private String password;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Order> orders = new ArrayList<>();
}
