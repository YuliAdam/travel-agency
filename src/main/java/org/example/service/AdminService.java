package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.controller.request.AdminRequest;
import org.example.controller.response.AdminResponse;
import org.example.entity.Admin;
import org.example.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public List<AdminResponse> getAllAdmin() {
        return adminRepository.findAll().stream().map(AdminResponse::new).toList();
    }

    public String deleteAdmin(Long id) {
        adminRepository.findById(id).get();
        adminRepository.deleteById(id);
        return "Deleted";
    }

    public AdminResponse createAdmin(AdminRequest adminRequest) {
        Admin admin = new Admin(adminRequest.getAdminName(), adminRequest.getPassword());
        return new AdminResponse(adminRepository.save(admin));
    }

    public AdminResponse updateAdmin(Long id, AdminRequest adminRequest) {
        Admin existingAdmin = adminRepository.findById(id).get();
        existingAdmin.setAdminName(adminRequest.getAdminName());
        existingAdmin.setPassword(adminRequest.getPassword());
        return new AdminResponse(adminRepository.save(existingAdmin));
    }

}

