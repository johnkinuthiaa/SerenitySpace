package com.slippery.serenityspace.services.impl;

import com.slippery.serenityspace.dto.EmergencyResourceDto;
import com.slippery.serenityspace.models.EmergencyResources;
import com.slippery.serenityspace.repository.EmergencyResourceRepository;
import com.slippery.serenityspace.services.EmergencyResourceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmergencyResourceServiceImplementation implements EmergencyResourceService {
    private final EmergencyResourceRepository resourceRepository;

    public EmergencyResourceServiceImplementation(EmergencyResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public EmergencyResourceDto createNewEmergencyResource(EmergencyResources emergencyResource) {
        EmergencyResourceDto response =new EmergencyResourceDto();
        emergencyResource.setCreatedOn(LocalDateTime.now());
        resourceRepository.save(emergencyResource);
        response.setMessage("New emergency service created");
        response.setStatusCode(200);
        response.setEmergencyResource(emergencyResource);
        return response;
    }

    @Override
    public EmergencyResourceDto updateEmergencyResource(Long emergencyId, EmergencyResources emergencyResource) {
        return null;
    }

    @Override
    public EmergencyResourceDto findEmergencyResourceById(Long emergencyId) {
        EmergencyResourceDto response =new EmergencyResourceDto();
        Optional<EmergencyResources> existingResource =resourceRepository.findById(emergencyId);
        if(existingResource.isEmpty()){
            response.setMessage("Resource not found");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Emergency resource with id"+emergencyId);
        response.setStatusCode(200);
        response.setEmergencyResource(existingResource.get());
        return response;
    }

    @Override
    public EmergencyResourceDto deleteEmergencyResourceById(Long emergencyId) {
        EmergencyResourceDto response =new EmergencyResourceDto();
        Optional<EmergencyResourceDto> checkExistingResource = Optional.ofNullable(findEmergencyResourceById(emergencyId));
        if(checkExistingResource.get().getStatusCode() !=200){
            response.setMessage(checkExistingResource.get().getMessage());
            response.setStatusCode(checkExistingResource.get().getStatusCode());
            return response;
        }
        resourceRepository.delete(checkExistingResource.get().getEmergencyResource());
        response.setStatusCode(200);
        response.setMessage("Emergency resource deleted!");
        return response;
    }

    @Override
    public EmergencyResourceDto findAllEmergencyResources() {
        EmergencyResourceDto response =new EmergencyResourceDto();
        var allResources =resourceRepository.findAll();
        if(allResources.isEmpty()){
            response.setMessage("Resource list is empty");
            response.setStatusCode(404);
            response.setEmergencyResources(new ArrayList<>());
            return response;
        }
        response.setEmergencyResources(allResources);
        response.setStatusCode(200);
        response.setMessage("All resources");
        return response;
    }

    @Override
    public EmergencyResourceDto deleteAllEmergencyResources() {
        EmergencyResourceDto response =new EmergencyResourceDto();
        Optional<EmergencyResourceDto> resourceList =Optional.of(findAllEmergencyResources());
        if(resourceList.get().getStatusCode() !=200){
            response.setMessage(resourceList.get().getMessage());
            response.setStatusCode(resourceList.get().getStatusCode());
            response.setEmergencyResources(new ArrayList<>());
            return response;
        }
        resourceRepository.deleteAll();
        response.setMessage("All resources deleted");
        response.setStatusCode(200);
        return null;
    }
}
