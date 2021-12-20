package com.example.myShop.domain.entity;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Entity
@Setter
@Getter
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_ID")
    int id;
    @Column(name = "descr")
    String descr;
}
