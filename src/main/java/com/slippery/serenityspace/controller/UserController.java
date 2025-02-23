package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.UsersDto;
import com.slippery.serenityspace.models.Users;
import com.slippery.serenityspace.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UsersService service;

    public UserController(UsersService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersDto> register(@Valid @RequestBody Users userDetails) {
        return ResponseEntity.ok(service.registerUser(userDetails));
    }
    @PostMapping("/login")
    public ResponseEntity<UsersDto> login(@RequestBody Users userDetails) {
        return ResponseEntity.ok(service.login(userDetails));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UsersDto> deleteUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.deleteUserById(userId));
    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findUserById(userId));
    }
    @GetMapping("/all-users")
    public ResponseEntity<UsersDto> findAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
    @PutMapping("/{userId}/upload/profile-photo")
    public ResponseEntity<UsersDto> uploadProfilePhoto(@PathVariable Long userId,@RequestPart MultipartFile image) throws IOException{
        return ResponseEntity.ok(service.uploadProfilePhoto(userId, image));
    }
}
