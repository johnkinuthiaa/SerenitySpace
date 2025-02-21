package com.slippery.serenityspace.repository;

import com.slippery.serenityspace.models.EmergencyResources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyResourceRepository extends JpaRepository<EmergencyResources,Long> {
}
