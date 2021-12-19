package com.example.myShop.models;

import javax.persistence.*;

@Entity
@Table(name = "Ordering")
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_ID")
    private int order_ID;
    @Column(name = "goods_ID")
    private int goodsID;
    @Column(name = "client_ID")
    private int clientID;
    @Column(name = "status_Name")
    private String statusName;
    @Column(name = "count")
    private int count;
    @Column(name = "price")
    private int price;
    @Column(name = "receive_ID")
    private int receiveID;
    @Column(name = "pay_Method")
    private String payMethod;

    public Ordering(){}

    public Ordering(int order_ID, int goods_ID, int client_ID, String status_Name, int count, int price, int receive_ID, String pay_Method) {
        this.order_ID = order_ID;
        this.goodsID = goods_ID;
        this.clientID = client_ID;
        this.statusName = status_Name;
        this.count = count;
        this.price = price;
        this.receiveID = receive_ID;
        this.payMethod = pay_Method;
    }

    public int getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(int order_ID) {
        this.order_ID = order_ID;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(int goods_ID) {
        this.goodsID = goods_ID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int client_ID) {
        this.clientID = client_ID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String status_Name) {
        this.statusName = status_Name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(int receive_ID) {
        this.receiveID = receive_ID;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String pay_Method) {
        this.payMethod = pay_Method;
    }
}
