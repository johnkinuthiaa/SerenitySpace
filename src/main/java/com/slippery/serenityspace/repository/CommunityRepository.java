package com.slippery.serenityspace.repository;

import com.slippery.serenityspace.models.Community;
import com.slippery.serenityspace.models.EmergencyResources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community,Long> {
}


