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
        try {
            adminRepository.findById(id).get();
            adminRepository.deleteById(id);
            return "Admin deleted";
        } catch (NoSuchElementException e){
            System.out.println("Error. Class adminService. Admin not found. "+ e);
            return "Admin not found. "+ e;
        }
    }
    public AdminResponse createAdmin(AdminRequest adminRequest){
        try {
        Admin admin =new Admin(adminRequest.getAdminName(),adminRequest.getPassword());
        return  new AdminResponse(adminRepository.save(admin));
        } catch (DataIntegrityViolationException e){
            System.out.println("Error. Class AdminService. Admin_name and password cannot be null. "+e);
            return new AdminResponse("Admin_name and password cannot be null. "+e);
        }
    }
    public AdminResponse updateAdmin(Long id, AdminRequest adminRequest){
        try {
        Admin existingAdmin = adminRepository.findById(id).get();
        existingAdmin.setAdminName(adminRequest.getAdminName());
        existingAdmin.setPassword(adminRequest.getPassword());
        return new AdminResponse(adminRepository.save(existingAdmin));
        }catch (NoSuchElementException e){
            System.out.println("Error. Class AdminService. Admin not found. "+e);
            return new AdminResponse("Admin not found. "+e);
        }catch (DataIntegrityViolationException e){
            System.out.println("Error. Class AdminService. Admin_name and password cannot be null. "+e);
            return new AdminResponse("Admin_name and password cannot be null. "+e);
        }
    }

    }

