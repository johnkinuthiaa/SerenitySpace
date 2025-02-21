package com.slippery.serenityspace.services;

import com.slippery.serenityspace.dto.UsersDto;
import com.slippery.serenityspace.models.Users;

public interface UsersService {
    UsersDto registerUser(Users user);
    UsersDto login(Users user);
    UsersDto updateUser(Long userId,Users userDetails);
    UsersDto deleteUserById(Long userId);
    UsersDto findUserById(Long userId);
    UsersDto findAllUsers();
}
