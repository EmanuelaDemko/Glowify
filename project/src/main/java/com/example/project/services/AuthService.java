package com.example.project.services;

import com.example.project.model.Role;
import com.example.project.model.User;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder encoder){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    public User registerUser(String username, String rawPassword, String fullName, String email, String roleName){
        if(userRepo.existsByUsername(username)) throw new RuntimeException("Username taken");
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(encoder.encode(rawPassword));
        user.setFullName(fullName);
        user.setEmail(email);
        Role r = roleRepo.findByName(roleName).orElseGet(() -> {
            Role nr = new Role();
            nr.setName(roleName);
            return roleRepo.save(nr);
        });
        user.getRoles().add(r);
        return userRepo.save(user);
    }


}
