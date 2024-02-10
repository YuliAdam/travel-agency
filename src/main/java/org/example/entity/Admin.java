package org.example.entity;

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
    public String admin_name;
    @Column(name ="password")
    public String password;
    public Admin(int id,String admin_name,String password){
        this.id=id;
        this.admin_name=admin_name;
        this.password=password;
    }
    public Admin(){

    }
}
