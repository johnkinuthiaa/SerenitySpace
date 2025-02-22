package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.MoodDto;
import com.slippery.serenityspace.models.Mood;
import com.slippery.serenityspace.services.MoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/moods")
public class MoodController {
    private final MoodService moodService;

    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }
    @PostMapping("/create")
    public ResponseEntity<MoodDto> registerNewMood(@RequestParam Long userId, @RequestBody Mood moodDetails) {
        return ResponseEntity.ok(moodService.registerNewMood(userId, moodDetails));
    }

    @GetMapping("/{moodId}/find")
    public ResponseEntity<MoodDto> getMoodById(@RequestParam Long userId,@PathVariable Long moodId) {
        return ResponseEntity.ok(moodService.findMoodById(userId, moodId));
    }

    @DeleteMapping("/{moodId}/delete")
    public ResponseEntity<MoodDto> deleteMoodById(@RequestParam Long userId,@PathVariable Long moodId) {
        return ResponseEntity.ok(moodService.deleteMoodById(userId, moodId));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<MoodDto> deleteAllMoodItemsForUser(@RequestParam Long userId) {
        return ResponseEntity.ok(moodService.deleteAllMoodItemsForUser(userId));
    }


}
