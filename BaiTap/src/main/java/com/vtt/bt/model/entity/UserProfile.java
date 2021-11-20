package com.vtt.bt.model.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "user_profile")
public class UserProfile {
    @Id
    private int id;
    private String username;
    private String password;
    private String address;
    private String email;
    private String fullname;
    @Column(name = "number_phone")
    private String numberphone;

    @Column(name = "created_time")
    @CreatedDate
    @CreationTimestamp
    private Timestamp createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
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