package com.slippery.serenityspace.services.impl;

import com.slippery.serenityspace.models.UserPrincipal;
import com.slippery.serenityspace.models.Users;
import com.slippery.serenityspace.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * @param username 
     * @return {@link UserPrincipal}
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> existingUsers =usersRepository.findByUsername(username);
        if(existingUsers.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(existingUsers.get());
    }
}
