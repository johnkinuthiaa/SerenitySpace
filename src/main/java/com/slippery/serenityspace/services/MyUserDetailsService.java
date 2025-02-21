package com.slippery.serenityspace.services;

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
    private final UsersRepository repository;

    public MyUserDetailsService(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users =repository.findByUsername(username);
        if(users.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(users.get());
    }
}
