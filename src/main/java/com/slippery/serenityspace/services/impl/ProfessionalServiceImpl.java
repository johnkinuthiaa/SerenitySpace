package com.slippery.serenityspace.services.impl;

import com.slippery.serenityspace.dto.ProfessionalInfoDto;
import com.slippery.serenityspace.models.Professionals;
import com.slippery.serenityspace.repository.ProfessionalsRepository;
import com.slippery.serenityspace.services.ProfessionalsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProfessionalServiceImpl implements ProfessionalsService {
    private final ProfessionalsRepository repository;

    public ProfessionalServiceImpl(ProfessionalsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfessionalInfoDto createNewProfessionalInfo(Professionals professionalsInfo) {
        ProfessionalInfoDto response =new ProfessionalInfoDto();
        Professionals existingByEmail =repository.findByEmail(professionalsInfo.getEmail());
        if(existingByEmail !=null){
            response.setMessage("Professional info about"+existingByEmail.getFullNames()+" already exists");
            response.setStatusCode(200);
            return response;
        }
        repository.save(professionalsInfo);
        response.setMessage("Professional info about"+professionalsInfo.getFullNames()+" saved successfully");
        response.setProfessionalInfo(professionalsInfo);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProfessionalInfoDto updateProfessionalInfo(Professionals professionalsInfo, Long infoId) {
        return null;
    }

    @Override
    public ProfessionalInfoDto findProfessionalInfoById(Long infoId) {
        Optional<Professionals> existing =repository.findById(infoId);
        ProfessionalInfoDto response =new ProfessionalInfoDto();
        if(existing.isEmpty()){
            response.setMessage("Info with id "+infoId+" does not exist");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Info with id "+infoId);
        response.setProfessionalInfo(existing.get());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProfessionalInfoDto deleteProfessionalInfoById(Long infoId) {
        var checkIfInfoExists =findProfessionalInfoById(infoId);
        ProfessionalInfoDto response =new ProfessionalInfoDto();
        if(checkIfInfoExists.getStatusCode() ==404){
            response.setMessage(checkIfInfoExists.getMessage());
            response.setStatusCode(checkIfInfoExists.getStatusCode());
            return response;
        }
        repository.deleteById(infoId);
        response.setMessage("Info with id"+ infoId+" deleted successfully");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProfessionalInfoDto findAllProfessionalInfos() {
        ProfessionalInfoDto response =new ProfessionalInfoDto();
        var infoList =repository.findAll();
        if(infoList.isEmpty()){
            response.setMessage("Info list is empty");
            response.setStatusCode(200);
            response.setProfessionalsList(new ArrayList<>());
            return response;
        }
        response.setMessage("All Info list");
        response.setStatusCode(200);
        response.setProfessionalsList(infoList);
        return response;
    }

    @Override
    public ProfessionalInfoDto deleteAllProfessionalInfos() {
        return null;
    }
}
