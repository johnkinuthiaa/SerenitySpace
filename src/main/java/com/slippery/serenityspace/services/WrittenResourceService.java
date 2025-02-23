package com.slippery.serenityspace.services;

import com.slippery.serenityspace.dto.WrittenResourcesDto;
import com.slippery.serenityspace.models.WrittenResources;

public interface WrittenResourceService {
    WrittenResourcesDto createNewResource(WrittenResources writtenResources);
    WrittenResourcesDto updateResource(WrittenResources writtenResources,Long resourceId);
    WrittenResourcesDto findResourceById(Long resourceId);
    WrittenResourcesDto deleteResourceById(Long resourceId);
    WrittenResourcesDto deleteAllResources();
    WrittenResourcesDto getAllResources();

}
