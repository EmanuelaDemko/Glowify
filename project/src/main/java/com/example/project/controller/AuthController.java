// AuthController.java
package com.example.project.controller;

import com.example.project.DTO.AuthResponse;
import com.example.project.DTO.RegisterRequest;
import com.example.project.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
