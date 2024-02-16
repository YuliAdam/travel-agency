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

    @GetMapping("/all")
    public List<Admin> getAllAdmin(){
        return adminService.getAllAdmin();
    }
    @DeleteMapping("/delete/id")
    public String deleteAdmin(@RequestParam int id){
        return adminService.deleteAdmin(id);
    }
    @PostMapping("/create")
    public Admin createAdmin(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }
    @PutMapping("/id")
    public Admin updateAdmin(@RequestParam int id,@RequestBody Admin admin){
        return adminService.updateAdmin(id,admin);
    }
}
