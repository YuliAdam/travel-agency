package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "admin_name")
    private String adminName;
    @Column(name ="password")
    private String password;

    public Admin(String adminName, String password){
        this.adminName=adminName;
        this.password=password;
    }
}
