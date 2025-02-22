package com.slippery.serenityspace.services.impl;

import com.slippery.serenityspace.dto.MoodDto;
import com.slippery.serenityspace.models.Mood;
import com.slippery.serenityspace.repository.MoodRepository;
import com.slippery.serenityspace.repository.UsersRepository;
import com.slippery.serenityspace.services.MoodService;
import org.springframework.stereotype.Service;

@Service
public class MoodServiceImplementation implements MoodService {
    private final MoodRepository repository;
    private final UsersRepository usersRepository;
    private final UsersServiceImplementation usersServiceImplementation;

    public MoodServiceImplementation(MoodRepository repository, UsersRepository usersRepository, UsersServiceImplementation usersServiceImplementation) {
        this.repository = repository;
        this.usersRepository = usersRepository;
        this.usersServiceImplementation = usersServiceImplementation;
    }

    @Override
    public MoodDto registerNewMood(Long userId, Mood moodDetails) {
        MoodDto response =new MoodDto();
        var existingUser =usersServiceImplementation.findUserById(userId);
        if(existingUser.getStatusCode() !=200){
            response.setMessage(existingUser.getMessage());
            response.setStatusCode(existingUser.getStatusCode());
            return response;
        }
        var userMoodsList =existingUser.getUser().getUserMoods();
        userMoodsList.add(moodDetails);
        existingUser.getUser().setUserMoods(userMoodsList);
        moodDetails.setUser(existingUser.getUser());
        repository.save(moodDetails);
        usersRepository.save(existingUser.getUser());
        response.setMessage("New mood item registered");
        response.setStatusCode(200);
        response.setMood(moodDetails);
        return response;
    }

    @Override
    public MoodDto updateMood(Long userId, Mood updateDetails) {
        MoodDto response =new MoodDto();

        return null;
    }

    @Override
    public MoodDto findMoodById(Long userId, Long moodId) {
        MoodDto response =new MoodDto();
        var existingUser =usersServiceImplementation.findUserById(userId);
        if(existingUser.getStatusCode() !=200){
            response.setMessage(existingUser.getMessage());
            response.setStatusCode(existingUser.getStatusCode());
            return response;
        }
        var userMoods =existingUser.getUser();
        var filterMood =userMoods.getUserMoods().stream()
                .filter(mood -> mood.getId().equals(moodId))
                .toList();
        if(filterMood.isEmpty()){
            response.setMessage("User does not have mood with id "+moodId);
            response.setStatusCode(404);
            return response;
        }
        response.setMood(filterMood.get(0));
        response.setStatusCode(200);
        response.setMessage("Mood with id "+moodId);
        return response;
    }

    @Override
    public MoodDto deleteMoodById(Long userId, Long moodId) {
        MoodDto response =new MoodDto();
        var existingMood =findMoodById(userId, moodId);
        if(existingMood.getStatusCode() !=200){
            response.setMessage(existingMood.getMessage());
            response.setStatusCode(existingMood.getStatusCode());
            return response;
        }
        var userMoods =existingMood.getMood().getUser().getUserMoods();
        userMoods.remove(existingMood.getMood());
        existingMood.getMood().getUser().setUserMoods(userMoods);
        usersRepository.save(existingMood.getMood().getUser());
        repository.deleteById(existingMood.getMood().getId());
        response.setMessage("Mood with id "+moodId+" deleted successfully!");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public MoodDto deleteAllMoodItemsForUser(Long userId) {
        MoodDto response =new MoodDto();
        var existingUser =usersServiceImplementation.findUserById(userId);
        if(existingUser.getStatusCode() !=200){
            response.setMessage(existingUser.getMessage());
            response.setStatusCode(existingUser.getStatusCode());
            return response;
        }
        var allMoodsByUser =existingUser.getUser().getUserMoods();
        allMoodsByUser.clear();
        existingUser.getUser().setUserMoods(allMoodsByUser);

        usersRepository.save(existingUser.getUser());
//        come back here
        response.setMoods(allMoodsByUser);
        response.setMessage("All moods for "+existingUser.getUser().getUsername() +" deleted successfully");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public MoodDto findAllMoodItemsForUser(Long userId) {
        return null;
    }
}
