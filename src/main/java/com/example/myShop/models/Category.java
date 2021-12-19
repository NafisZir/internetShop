package com.example.myShop.models;

import javax.persistence.*;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_ID")
    private int categoryID;
    @Column(name = "descr")
    private String descr;

    public Category(int category_ID, String descr) {
        this.categoryID = category_ID;
        this.descr = descr;
    }

    public Category(){}

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int category_ID) {
        this.categoryID = category_ID;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
