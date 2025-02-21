package com.slippery.serenityspace.repository;

import com.slippery.serenityspace.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
    @Query("SELECT  DISTINCT Users WHERE Users.email = ?")
    Optional<Users> findByEmail(String email);
}
