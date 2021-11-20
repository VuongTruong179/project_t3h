package com.vtt.bt.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "rol")
public class Rol {
    @Id
    private int id;
    private String rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}