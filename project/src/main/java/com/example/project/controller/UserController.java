// UserController.java
package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User create(@RequestBody User user) {
        user.setId(null);
        return userRepo.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User body) {
        return userRepo.findById(id).map(existing -> {
            if (body.getUsername() != null) existing.setUsername(body.getUsername());
            if (body.getPassword() != null) existing.setPassword(body.getPassword());
            if (body.getFullName() != null) existing.setFullName(body.getFullName());
            if (body.getEmail() != null) existing.setEmail(body.getEmail());
            if (body.getPhone() != null) existing.setPhone(body.getPhone());
            if (body.getRoles() != null && !body.getRoles().isEmpty()) existing.setRoles(body.getRoles());
            return ResponseEntity.ok(userRepo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!userRepo.existsById(id)) return ResponseEntity.notFound().build();
        userRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
