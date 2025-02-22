package com.slippery.serenityspace.services;

import com.slippery.serenityspace.dto.EmergencyResourceDto;
import com.slippery.serenityspace.models.EmergencyResources;

public interface EmergencyResourceService {
    EmergencyResourceDto createNewEmergencyResource(EmergencyResources emergencyResource);
    EmergencyResourceDto updateEmergencyResource(Long emergencyId,EmergencyResources emergencyResource);
    EmergencyResourceDto findEmergencyResourceById(Long emergencyId);
    EmergencyResourceDto deleteEmergencyResourceById(Long emergencyId);
    EmergencyResourceDto findAllEmergencyResources();
    EmergencyResourceDto deleteAllEmergencyResources();
}

