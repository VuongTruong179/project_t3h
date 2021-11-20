package com.vtt.bt.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserRol {

    @Id
    private int Id;
    @Column(name = "user_of_id")
    private int userOfId;
    @Column(name = "rol_id")
    private int rolId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserOfId() {
        return userOfId;
    }

    public void setUserOfId(int userOfId) {
        this.userOfId = userOfId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }
}
