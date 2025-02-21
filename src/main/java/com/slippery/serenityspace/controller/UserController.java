package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.UsersDto;
import com.slippery.serenityspace.models.Users;
import com.slippery.serenityspace.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UsersService service;

    public UserController(UsersService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersDto> register(@RequestBody Users userDetails) {
        return ResponseEntity.ok(service.registerUser(userDetails));
    }
    @PostMapping("/login")
    public ResponseEntity<UsersDto> login(@RequestBody Users userDetails) {
        return ResponseEntity.ok(service.login(userDetails));
    }
}
