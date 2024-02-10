package org.example.service;


import org.example.entity.Admin;
import org.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    public List<Admin> getAllAdmin(){
        return adminRepository.findAllAdmin().stream().toList();
    }
}
