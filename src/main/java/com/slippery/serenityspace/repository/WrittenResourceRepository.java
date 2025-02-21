package com.slippery.serenityspace.repository;

import com.slippery.serenityspace.models.WrittenResources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WrittenResourceRepository extends JpaRepository<WrittenResources,Long> {
}
