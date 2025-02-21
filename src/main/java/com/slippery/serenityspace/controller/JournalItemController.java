package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.JournalDto;
import com.slippery.serenityspace.models.Journal;
import com.slippery.serenityspace.services.JournalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/journals")
public class JournalItemController {
    private final JournalService service;

    public JournalItemController(JournalService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<JournalDto> createJournalItem(@RequestBody Journal journal,@RequestParam Long userId) {
        return ResponseEntity.ok(service.createNewJournal(journal, userId));
    }

    @DeleteMapping("/delete-journal")
    public ResponseEntity<JournalDto> deleteJournalById(@RequestParam Long journalId,@RequestParam Long userId) {
        return ResponseEntity.ok(service.deleteJournalById(journalId, userId));
    }

    @GetMapping("/{userId}/get/journals")
    public ResponseEntity<JournalDto> getAllJournalsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getAllJournalsByUser(userId));
    }

    @GetMapping("/get/{journalId}/")
    public ResponseEntity<JournalDto> getJournalById(@PathVariable Long journalId,@RequestParam Long userId) {
        return ResponseEntity.ok(service.getJournalById(journalId, userId));
    }

}
