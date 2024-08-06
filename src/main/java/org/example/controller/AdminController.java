package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.AdminRequest;
import org.example.controller.response.AdminResponse;
import org.example.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public List<AdminResponse> getAllAdmin() {
        return adminService.getAllAdmin();
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        return adminService.deleteAdmin(id);
    }

    @PostMapping
    public AdminResponse createAdmin(@RequestBody AdminRequest adminRequest) {
        return adminService.createAdmin(adminRequest);
    }

    @PutMapping("/{id}")
    public AdminResponse updateAdmin(@PathVariable Long id, @RequestBody AdminRequest adminRequest) {
        return adminService.updateAdmin(id, adminRequest);
    }
}
