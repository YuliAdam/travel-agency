package org.example.service;


import org.example.controller.request.AdminRequest;
import org.example.controller.response.AdminResponse;
import org.example.entity.Admin;
import org.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    public List<AdminResponse> getAllAdmin(){
        return adminRepository.findAll().stream().map(AdminResponse::new).toList();
    }
    public String deleteAdmin(Long id){
            adminRepository.findById(id).get();
            adminRepository.deleteById(id);
            return "Admin deleted";
    }
    public AdminResponse createAdmin(AdminRequest adminRequest){
        Admin admin =new Admin(adminRequest.getAdminName(),adminRequest.getPassword());
        return  new AdminResponse(adminRepository.save(admin));
    }
    public AdminResponse updateAdmin(Long id, AdminRequest adminRequest){
        Admin existingAdmin = adminRepository.findById(id).get();
        existingAdmin.setAdminName(adminRequest.getAdminName());
        existingAdmin.setPassword(adminRequest.getPassword());
        return new AdminResponse(adminRepository.save(existingAdmin));
    }

    }

