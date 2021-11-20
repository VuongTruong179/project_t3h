package com.vtt.bt.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "oder")
public class Oder {
    @Id
    private int id;

    @Column(name ="sum_all_total_oder")
    private int totalBill;

    @Column(name ="id_of_table")
    private int idOfTable;


    @Column(name ="created_time")
    @CreatedDate
    @CreationTimestamp
    private Timestamp createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public int getIdOfTable() {
        return idOfTable;
    }

    public void setIdOfTable(int idOfTable) {
        this.idOfTable = idOfTable;
    }


    @CreatedDate
    @CreationTimestamp
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    @CreatedDate
    @CreationTimestamp
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}