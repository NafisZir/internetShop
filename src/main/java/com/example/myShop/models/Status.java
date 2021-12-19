package com.example.myShop.models;

import javax.persistence.*;

@Entity
@Table(name = "Status")
public class Status {
    @Id
    @Column(name = "status_Name")
    private String status_Name;

    public Status(){}

    public Status(String status_Name) {
        this.status_Name = status_Name;
    }

    public String getStatus_Name() {
        return status_Name;
    }

    public void setStatus_Name(String status_Name) {
        this.status_Name = status_Name;
    }
}
