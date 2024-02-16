package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="admin")
public class Admin {
    @Id
    public int id;
    @Column(name = "admin_name")
    @Nullable
    public String adminName;
    @Column(name ="password")
    public String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(int id, String adminName, String password){
        this.id=id;
        this.adminName=adminName;
        this.password=password;
    }
    public Admin(){

    }
}
