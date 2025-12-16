package com.example.project.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(nullable=false)
    private String passwordHash;

    @Column(nullable=false)
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles",
      joinColumns = @JoinColumn(name="user_id"),
      inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();

    // contact fields
    private String email;
    private String phone;

    // getters/setters
    // ...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}
    public String getPasswordHash(){return passwordHash;}
    public void setPasswordHash(String passwordHash){this.passwordHash=passwordHash;}
    public String getFullName(){return fullName;}
    public void setFullName(String fullName){this.fullName=fullName;}
    public Set<Role> getRoles(){return roles;}
    public void setRoles(Set<Role> roles){this.roles=roles;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone=phone;}
}
