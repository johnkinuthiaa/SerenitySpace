package com.slippery.serenityspace.services.impl;

import com.slippery.serenityspace.dto.JournalDto;
import com.slippery.serenityspace.models.Journal;
import com.slippery.serenityspace.models.Users;
import com.slippery.serenityspace.repository.JournalRepository;
import com.slippery.serenityspace.repository.UsersRepository;
import com.slippery.serenityspace.services.JournalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JournalServiceImplementation implements JournalService {
    private final UsersRepository usersRepository;
    private final JournalRepository journalRepository;


    public JournalServiceImplementation(UsersRepository usersRepository, JournalRepository journalRepository) {
        this.usersRepository = usersRepository;
        this.journalRepository = journalRepository;
    }

    @Override
    public JournalDto createNewJournal(Journal journalDetails, Long userId) {
        Optional<Users> existingUser =usersRepository.findById(userId);
        JournalDto response =new JournalDto();
        if(existingUser.isEmpty()){
            response.setMessage("User does not exist");
            response.setStatusCode(404);
            return response;
        }
        journalDetails.setUser(existingUser.get());
        journalRepository.save(journalDetails);

        var userJournals =existingUser.get().getUserJournals();
        userJournals.add(journalDetails);
        existingUser.get().setUserJournals(userJournals);
        usersRepository.save(existingUser.get());
        response.setStatusCode(201);
        response.setMessage("New journal item created");

        return response;
    }

    @Override
    public JournalDto updateJournal(Journal journalDetails, Long journalId, Long userId) {
        return null;
    }

    @Override
    public JournalDto deleteJournalById(Long journalId, Long userId) {
//        use the get by id method response
        var journalExists =getJournalById(journalId,userId);
        JournalDto response =new JournalDto();
        if(journalExists.getStatusCode() !=200){
            response.setMessage(journalExists.getMessage());
            response.setStatusCode(journalExists.getStatusCode());
            return response;
        }

        var userJournals =journalExists.getJournalItem().getUser().getUserJournals();
        userJournals.remove(journalExists.getJournalItem());
        journalExists.getJournalItem().getUser().setUserJournals(userJournals);
        usersRepository.save(journalExists.getJournalItem().getUser());
        journalRepository.deleteById(journalId);
        response.setStatusCode(200);
        response.setMessage("Journal item deleted");
        return response;
    }

    @Override
    public JournalDto getJournalById(Long journalId, Long userId) {
        Optional<Users> existingUser =usersRepository.findById(userId);
        Optional<Journal> existingJournal =journalRepository.findById(journalId);
        JournalDto response =new JournalDto();
        if(existingUser.isEmpty()){
            response.setMessage("User does not exist");
            response.setStatusCode(404);
            return response;
        }
        if(existingJournal.isEmpty()){
            response.setMessage("Journal not found");
            response.setStatusCode(404);
            return response;
        }
        if(!existingJournal.get().getUser().getId().equals(existingUser.get().getId())){
            response.setMessage("Journal does not belong to user");
            response.setStatusCode(404);
            return response;
        }
        response.setJournalItem(existingJournal.get());
        response.setMessage("Journal with id"+journalId);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public JournalDto getAllJournalsByUser(Long userId) {
        JournalDto response =new JournalDto();
        Optional<Users> existingUser =usersRepository.findById(userId);
        if(existingUser.isEmpty()){
            response.setMessage("User does not exist");
            response.setStatusCode(404);
            return response;
        }
        var allJournals =existingUser.get().getUserJournals();
        if(allJournals.isEmpty()){
            response.setStatusCode(200);
            response.setMessage("User has no active journal item");
            return response;
        }
        response.setJournalItems(allJournals);
        response.setStatusCode(200);
        response.setMessage("All journal items for "+existingUser.get().getUsername());
        return response;
    }
}
