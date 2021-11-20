package com.vtt.bt.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name ="order_details")
public class OrderDetail {
    @Id
    private int id;
    @Column(name ="id_table")
    private int idTable;
    @Column(name ="menu_id")
    private int menuID;

    private int quantity;
    @Column(name ="total_price")
    private int totalPrice;


    @Column(name ="created_time")
    @CreatedDate
    @CreationTimestamp
    private Timestamp createdTime;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getMenuID() {
        return menuID;
    }

    public int setMenuID(int menuID) {
        this.menuID = menuID;
        return menuID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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