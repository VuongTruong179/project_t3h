package com.vtt.bt.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Messenger {
    @Id
    private int id;
    private int sender;
    private int receiver;
    private String content;

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

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
