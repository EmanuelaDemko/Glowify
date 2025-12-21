package com.example.project.service;

import com.example.project.DTO.AuthResponse;
import com.example.project.DTO.RegisterRequest;
import com.example.project.model.Role;
import com.example.project.model.User;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public AuthService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    private String resolveRoleName(RegisterRequest request) {
        if (request.isRegisterAsStaff()) {
            return "ROLE_STAFF";
        }
        if (request.isRegisterAsClient()) {
            return "ROLE_CLIENT";
        }
        return "ROLE_USER";
    }


    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());

        user.setPassword(request.getPassword());

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        String roleName = resolveRoleName(request);
        Role role = roleRepo.findByName(roleName).orElseGet(() -> {
            Role r = new Role();
            r.setName(roleName);
            return roleRepo.save(r);
        });

        user.getRoles().add(role);
        userRepo.save(user);

        return new AuthResponse("", user.getUsername(), roleName);
    }
}

