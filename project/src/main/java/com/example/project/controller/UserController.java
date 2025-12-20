package com.example.project.controller;

import com.example.project.dto.UserDTO;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRoles().stream()
                .findFirst().map(r -> r.getName()).orElse("USER"));

        return dto;
    }
}
