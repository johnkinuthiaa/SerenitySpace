package com.slippery.serenityspace.services.impl;

import com.slippery.serenityspace.dto.UsersDto;
import com.slippery.serenityspace.models.Users;
import com.slippery.serenityspace.repository.UsersRepository;
import com.slippery.serenityspace.services.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsersServiceImplementation implements UsersService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UsersServiceImplementation(UsersRepository repository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UsersDto registerUser(Users user) {
        UsersDto response =new UsersDto();

        Optional<Users> existingUsersByUsername =repository.findByUsername(user.getUsername());
        Optional<Users> existingUsersByEmail =repository.findByEmail(user.getEmail());
        if(existingUsersByEmail.isPresent()){
            response.setMessage("User with email "+user.getEmail()+" already exists. Please log in to your account or use a different email.");
            response.setStatusCode(200);
            return response;
        }
        if(existingUsersByUsername.isPresent()){
            response.setMessage("User with username "+user.getUsername()+" already exists. Please try another username");
            response.setStatusCode(200);
            return response;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfilePhoto(null);
        user.setCreatedOn(LocalDateTime.now());
        repository.save(user);
        return response;
    }

    @Override
    public UsersDto login(Users user) {
        UsersDto response =new UsersDto();
//        login with email only and password
        Optional<Users> existingUsersByEmail =repository.findByEmail(user.getEmail());

        if(existingUsersByEmail.isEmpty()){
            response.setMessage("User with username "+user.getEmail()+" does not exist. Please try another email or log in ");
            response.setStatusCode(200);
            return response;
        }
        var username =existingUsersByEmail.get().getUsername();
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username,user.getPassword()
        ));
        if(!authentication.isAuthenticated()){
            response.setMessage("User not authenticated");
            response.setStatusCode(401);
            return response;
        }
        var jwtToken =jwtService.generateJwtToken(user.getUsername());
        response.setJwtToken(jwtToken);
        response.setStatusCode(200);
        response.setMessage("User "+username+" logged in successfully");
        return response;
    }


    @Override
    public UsersDto updateUser(Long userId, Users userDetails) {
        return null;
    }

    @Override
    public UsersDto deleteUserById(Long userId) {
        return null;
    }

    @Override
    public UsersDto findUserById(Long userId) {
        return null;
    }

    @Override
    public UsersDto findAllUsers() {
        return null;
    }
}
