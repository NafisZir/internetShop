package com.example.myShop.models;

import javax.persistence.*;

@Entity
@Table(name = "Goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_ID")
    private int goods_ID;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "producer_Name")
    private String producer_Name;
    @Column(name = "category_ID")
    private int categoryID;
    @Column(name = "availability")
    private int availability;
    @Column(name = "image")
    private String image;

    public Goods(int goods_ID, String name, int price, String producer_Name, int category_ID, int availability, String image) {
        this.goods_ID = goods_ID;
        this.name = name;
        this.price = price;
        this.producer_Name = producer_Name;
        this.categoryID = category_ID;
        this.availability = availability;
        this.image = image;
    }

    public Goods(){}

    public void decAvailability(Integer count){
        availability -= count;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public int getGoods_ID() {
        return goods_ID;
    }

    public void setGoods_ID(int goods_ID) {
        this.goods_ID = goods_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProducer_Name() {
        return producer_Name;
    }

    public void setProducer_Name(String producer_Name) {
        this.producer_Name = producer_Name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int category_ID) {
        this.categoryID = category_ID;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

}
