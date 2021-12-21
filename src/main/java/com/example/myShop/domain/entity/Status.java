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
@Table(name = "Status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "status_Name")
    String status;
}
