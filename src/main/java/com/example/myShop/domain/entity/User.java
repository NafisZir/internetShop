package com.example.myShop.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();
}
