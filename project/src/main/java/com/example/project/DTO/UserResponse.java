package com.example.project.DTO;

public class UserResponse {

    private Long id;
    private String username;
    private String fullName;
    private String email;

    public UserResponse(Long id, String username, String fullName, String email) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}

