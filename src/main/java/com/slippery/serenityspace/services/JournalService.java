package com.slippery.serenityspace.services;

import com.slippery.serenityspace.dto.JournalDto;
import com.slippery.serenityspace.models.Journal;

public interface JournalService {
    JournalDto createNewJournal(Journal journalDetails,Long userId);
    JournalDto updateJournal(Journal journalDetails,Long journalId,Long userId);
    JournalDto deleteJournalById(Long journalId,Long userId);
    JournalDto getJournalById(Long journalId,Long userId);
    JournalDto getAllJournalsByUser(Long userId);
}
