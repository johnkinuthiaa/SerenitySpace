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
import java.util.ArrayList;
import java.util.List;
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
        user.setUserJournals(new ArrayList<>());
        repository.save(user);
        response.setMessage("User created successfully");
        response.setUser(user);
        response.setStatusCode(201);
        return response;
    }

    @Override
    public UsersDto login(Users user) {
        UsersDto response =new UsersDto();
//        login with email only and password. Get the username for authentication from the email of existing user
        Optional<Users> existingUsersByEmail =repository.findByEmail(user.getEmail());

        if(existingUsersByEmail.isEmpty()){
            response.setMessage("User with email "+user.getEmail()+" does not exist. Please try another email or register for an account ");
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
        UsersDto response =new UsersDto();
        Optional<UsersDto> existingUser =Optional.of(findUserById(userId));
        if(existingUser.get().getStatusCode() !=200){
            response.setMessage(existingUser.get().getMessage());
            response.setStatusCode(existingUser.get().getStatusCode());
            return response;
        }
        repository.deleteById(existingUser.get().getUser().getId());
        response.setMessage("User deleted successfully ");
        response.setStatusCode(204);
        return response;
    }

    @Override
    public UsersDto findUserById(Long userId) {
        UsersDto response =new UsersDto();
        Optional<Users> existing =repository.findById(userId);
        if(existing.isEmpty()){
            response.setMessage("User with id "+userId+" not exist!");
            response.setStatusCode(404);
            return response;
        }
        response.setUser(existing.get());
        response.setStatusCode(200);
        response.setMessage("User with id "+userId);
        return response;
    }

    @Override
    public UsersDto getAllUsers() {
        UsersDto response =new UsersDto();
        List<Users> userList =repository.findAll();
        if(userList.isEmpty()){
            response.setMessage("No users present");
            response.setStatusCode(404);
            return response;
        }
        response.setUsers(userList);
        response.setMessage("All registered users");
        response.setStatusCode(200);

        return response;
    }
}
