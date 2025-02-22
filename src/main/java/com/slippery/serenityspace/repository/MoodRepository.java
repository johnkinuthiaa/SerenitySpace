package com.slippery.serenityspace.repository;

import com.slippery.serenityspace.models.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MoodRepository extends JpaRepository<Mood,Long> {

}
