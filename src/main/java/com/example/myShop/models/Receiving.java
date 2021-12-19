package com.example.myShop.models;

import javax.persistence.*;

@Entity
@Table(name = "Receiving")
public class Receiving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receive_ID")
    private int receive_ID;
    @Column(name = "receive_Method")
    private String receive_Method;
    @Column(name = "address")
    private String address;

    public Receiving(){}

    public Receiving(int receive_ID, String receive_Method, String address) {
        this.receive_ID = receive_ID;
        this.receive_Method = receive_Method;
        this.address = address;
    }

    public int getReceive_ID() {
        return receive_ID;
    }

    public void setReceive_ID(int receive_ID) {
        this.receive_ID = receive_ID;
    }

    public String getReceive_Method() {
        return receive_Method;
    }

    public void setReceive_Method(String receive_Method) {
        this.receive_Method = receive_Method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
