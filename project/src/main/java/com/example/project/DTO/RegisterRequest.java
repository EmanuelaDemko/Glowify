package com.example.project.dto;

public class RegisterRequest {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;

    private boolean registerAsClient = true;
    private boolean registerAsStaff = false;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public boolean isRegisterAsClient() { return registerAsClient; }
    public void setRegisterAsClient(boolean registerAsClient) { this.registerAsClient = registerAsClient; }

    public boolean isRegisterAsStaff() { return registerAsStaff; }
    public void setRegisterAsStaff(boolean registerAsStaff) { this.registerAsStaff = registerAsStaff; }
}

