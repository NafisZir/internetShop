package com.example.myShop.models;

import javax.persistence.*;

@Entity
@Table(name = "Producer")
public class Producer {
    @Id
    @Column(name = "producer_Name")
    private String producer_Name;
    @Column(name = "country")
    private String country;

    public Producer(){}

    public Producer(String producer_Name, String country) {
        this.producer_Name = producer_Name;
        this.country = country;
    }

    public String getProducer_Name() {
        return producer_Name;
    }

    public void setProducer_Name(String producer_Name) {
        this.producer_Name = producer_Name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
