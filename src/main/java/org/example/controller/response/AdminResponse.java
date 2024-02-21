package org.example.controller.response;
import org.example.entity.Admin;

public class AdminResponse {
    private Long id;
    private String adminName;

    public void setId(Long id) {
        this.id = id;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Long getId() {
        return id;
    }

    public String getAdminName() {
        return adminName;
    }

    public AdminResponse(Admin admin){
        this.id=admin.getId();
        this.adminName=admin.getAdminName();
    }
    public AdminResponse(Long id, String adminName){
        this.id=id;
        this.adminName=adminName;
    }
    public AdminResponse( String adminName){
        this.adminName=adminName;
    }
    public AdminResponse(){}
}
