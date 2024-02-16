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
    public String deleteAdmin(int id){
        try {
            adminRepository.findById(id).get();
            adminRepository.deleteById(id);
            return "Admin deleted";
        } catch (Exception e){
            return "Admin not found";
        }
    }
    public Admin createAdmin(Admin admin){
        return  adminRepository.save(admin);
    }
    public Admin updateAdmin(int id, Admin admin){
        Admin existingAdmin = adminRepository.findById(id).get();
        existingAdmin.setAdminName(admin.getAdminName());
        existingAdmin.setPassword(admin.getPassword());
        return adminRepository.save(existingAdmin);
    }

    }
