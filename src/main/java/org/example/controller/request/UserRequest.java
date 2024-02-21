package org.example.controller.request;


public class UserRequest {

    private String userName;

    private String email;

    private Long tell;

    public UserRequest(String userName, String email, Long tell) {
        this.userName = userName;
        this.email = email;
        this.tell = tell;
    }

    public UserRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Long getTell() {
        return tell;
    }
}
