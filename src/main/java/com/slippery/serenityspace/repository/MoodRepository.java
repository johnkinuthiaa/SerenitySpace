package com.slippery.serenityspace.repository;

import com.slippery.serenityspace.models.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<Mood,Long> {
}
