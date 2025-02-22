package com.slippery.serenityspace.services;

import com.slippery.serenityspace.dto.MoodDto;
import com.slippery.serenityspace.models.Mood;

public interface MoodService {
    MoodDto registerNewMood(Long userId, Mood moodDetails);
    MoodDto updateMood(Long userId, Mood updateDetails);
    MoodDto findMoodById(Long userId, Long moodId);
    MoodDto deleteMoodById(Long userId, Long moodId);
    MoodDto deleteAllMoodItemsForUser(Long userId);
    MoodDto findAllMoodItemsForUser(Long userId);

}
