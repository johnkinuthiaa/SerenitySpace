package com.slippery.serenityspace.services;

import com.slippery.serenityspace.dto.ProfessionalInfoDto;
import com.slippery.serenityspace.models.Professionals;

public interface ProfessionalsService {
    ProfessionalInfoDto createNewProfessionalInfo(Professionals professionalsInfo);
    ProfessionalInfoDto updateProfessionalInfo(Professionals professionalsInfo,Long infoId);
    ProfessionalInfoDto findProfessionalInfoById(Long infoId);
    ProfessionalInfoDto deleteProfessionalInfoById(Long infoId);
    ProfessionalInfoDto findAllProfessionalInfos();
    ProfessionalInfoDto deleteAllProfessionalInfos();

}
