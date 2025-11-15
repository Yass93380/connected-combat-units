package com.example.yass.users.controller;

import com.example.yass.users.model.dto.UserCreateOrUpdateDto;
import com.example.yass.users.model.entity.User;
import com.example.yass.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Set<User>> users() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> user(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<Set<User>> createUsers(@Valid @RequestBody Set<UserCreateOrUpdateDto> usersDto) {
        return ResponseEntity.ok(userService.createAllUsers(usersDto));
    }
}
