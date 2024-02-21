package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name ="admin")
public class Admin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "admin_name")
    private String adminName;
    @Column(name ="password")
    private String password;

    public Long getId() {return id;}

    public void setId(Long id) {
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

    public Admin(Long id, String adminName, String password){
        this.id=id;
        this.adminName=adminName;
        this.password=password;
    }
    public Admin(String adminName, String password){
        this.adminName=adminName;
        this.password=password;
    }
    public Admin(){}
}
