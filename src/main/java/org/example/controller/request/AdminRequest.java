package org.example.controller.request;

public class AdminRequest {
    private Long id;

    private String adminName;
    private String password;
    private Long getId() {return id;}
    public String getAdminName() {
        return adminName;
    }

    public String getPassword() {
        return password;
    }

    public AdminRequest(Long id, String adminName,String password){
        this.id=id;
        this.adminName=adminName;
        this.password=password;
    }
    public AdminRequest(){}
}
