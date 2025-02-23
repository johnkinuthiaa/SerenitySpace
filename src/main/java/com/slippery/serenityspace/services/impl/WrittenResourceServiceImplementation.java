package com.slippery.serenityspace.services.impl;

import com.slippery.serenityspace.dto.WrittenResourcesDto;
import com.slippery.serenityspace.models.WrittenResources;
import com.slippery.serenityspace.repository.WrittenResourceRepository;
import com.slippery.serenityspace.services.WrittenResourceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WrittenResourceServiceImplementation implements WrittenResourceService {
    private final WrittenResourceRepository repository;

    public WrittenResourceServiceImplementation(WrittenResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public WrittenResourcesDto createNewResource(WrittenResources writtenResources) {
        WrittenResourcesDto response =new WrittenResourcesDto();
        repository.save(writtenResources);
        response.setMessage("New Resource saved");
        response.setWrittenResource(writtenResources);
        response.setStatusCode(200);
        return response;
    }
    @Override
    public WrittenResourcesDto updateResource(WrittenResources writtenResources, Long resourceId) {
        return null;
    }

    @Override
    public WrittenResourcesDto findResourceById(Long resourceId) {
        WrittenResourcesDto response =new WrittenResourcesDto();
        Optional<WrittenResources> existingResource =repository.findById(resourceId);
        if(existingResource.isEmpty()){
            response.setMessage("No resource with id "+resourceId+" was found!");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Resource with id"+ resourceId);
        response.setStatusCode(200);
        response.setWrittenResource(existingResource.get());
        return response;
    }

    @Override
    public WrittenResourcesDto deleteResourceById(Long resourceId) {
        WrittenResourcesDto response =new WrittenResourcesDto();
        var existingResource =findResourceById(resourceId);
        if(existingResource.getStatusCode() !=200){
            return existingResource;
        }
        repository.deleteById(resourceId);
        response.setMessage("Resource with id "+resourceId+" deleted successfully");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public WrittenResourcesDto deleteAllResources() {
        WrittenResourcesDto response =new WrittenResourcesDto();
        var listExists =getAllResources();
        if(listExists.getStatusCode() !=200){
            return listExists;
        }
        repository.deleteAll();
        response.setMessage("All resources deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public WrittenResourcesDto getAllResources() {
        WrittenResourcesDto response =new WrittenResourcesDto();
        var allResources =repository.findAll();
        if(allResources.isEmpty()){
            response.setMessage("Resource list is empty");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("All existing resources");
        response.setStatusCode(200);
        response.setWrittenResources(allResources);
        return null;
    }
}
