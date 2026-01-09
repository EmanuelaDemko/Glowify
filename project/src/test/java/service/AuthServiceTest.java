package com.example.project.service;

import com.example.project.DTO.AuthResponse;
import com.example.project.DTO.RegisterRequest;
import com.example.project.model.Role;
import com.example.project.model.User;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    UserRepository userRepo;

    @Mock
    RoleRepository roleRepo;

    @InjectMocks
    AuthService authService;

    @Test
    void registerClient_success() {
        RegisterRequest req = new RegisterRequest();
        req.setUsername("test");
        req.setRegisterAsClient(true);

        when(userRepo.existsByUsername("test")).thenReturn(false);
        when(roleRepo.findByName("ROLE_CLIENT")).thenReturn(Optional.empty());
        when(roleRepo.save(any())).thenAnswer(i -> i.getArgument(0));
        when(userRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        AuthResponse res = authService.register(req);

        assertEquals("test", res.getUsername());
        assertEquals("ROLE_CLIENT", res.getRole());
    }

    @Test
    void registerUser_usernameExists() {
        RegisterRequest req = new RegisterRequest();
        req.setUsername("test");

        when(userRepo.existsByUsername("test")).thenReturn(true);

        assertThrows(RuntimeException.class,
                () -> authService.register(req));
    }

    @Test
    void registerStaffRole() {
        RegisterRequest req = new RegisterRequest();
        req.setRegisterAsStaff(true);

        when(userRepo.existsByUsername(any())).thenReturn(false);
        when(roleRepo.findByName("ROLE_STAFF"))
                .thenReturn(Optional.of(new Role()));
        when(userRepo.save(any())).thenReturn(new User());

        AuthResponse res = authService.register(req);

        assertEquals("ROLE_STAFF", res.getRole());
    }
}

