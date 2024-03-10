package org.example.controller.request;


public class UserRequest {

    private String userName;

    private String email;

    private Long tell;
    private Long id;

    public UserRequest(String userName, String email, Long tell) {
        this.userName = userName;
        this.email = email;
        this.tell = tell;
    }
    public UserRequest(Long id,String userName, String email, Long tell) {
        this.id=id;
        this.userName = userName;
        this.email = email;
        this.tell = tell;
    }

    public UserRequest() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTell(Long tell) {
        this.tell = tell;
    }
}
