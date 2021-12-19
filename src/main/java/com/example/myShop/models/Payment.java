package com.example.myShop.models;

import javax.persistence.*;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @Column(name = "pay_Method")
    private String pay_Method;

    public Payment(){}

    public Payment(String pay_Method) {
        this.pay_Method = pay_Method;
    }

    public String getPay_Method() {
        return pay_Method;
    }

    public void setPay_Method(String pay_Method) {
        this.pay_Method = pay_Method;
    }
}
