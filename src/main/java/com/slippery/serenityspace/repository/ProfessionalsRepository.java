package com.slippery.serenityspace.repository;

import com.slippery.serenityspace.models.Professionals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalsRepository extends JpaRepository<Professionals,Long> {
    Professionals findByEmail(String email);
}
