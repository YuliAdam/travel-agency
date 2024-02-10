package org.example.controller;

import org.example.entity.Admin;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/allAdmin")
    public List<Admin> getAllAdmin(){
        return adminService.getAllAdmin();
    }
}
