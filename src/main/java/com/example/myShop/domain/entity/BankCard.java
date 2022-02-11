package com.example.myShop.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author nafis
 * @since 09.02.2022
 */

@Entity
@Setter
@Getter
@Table(name = "bank_cards")
public class BankCard extends BaseEntity{
    private String number;
    private Byte month;
    private Byte year;
    @Column(name = "back_number")
    private Short backNumber;

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
