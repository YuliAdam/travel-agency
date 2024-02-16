package org.example.controller;

import org.example.entity.Admin;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping
    public List<Admin> getAllAdmin(){
        return adminService.getAllAdmin();
    }
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable int id){
        return adminService.deleteAdmin(id);
    }
    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }
    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable int id,@RequestBody Admin admin){
        return adminService.updateAdmin(id,admin);
    }
}
