package com.slippery.serenityspace.repository;

import com.slippery.serenityspace.models.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal,Long> {
}
