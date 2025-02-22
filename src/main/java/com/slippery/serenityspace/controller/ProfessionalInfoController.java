package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.ProfessionalInfoDto;
import com.slippery.serenityspace.models.Professionals;
import com.slippery.serenityspace.services.ProfessionalsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/professionalInfo")
public class ProfessionalInfoController {
    private final ProfessionalsService service;

    public ProfessionalInfoController(ProfessionalsService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<ProfessionalInfoDto> createNewProfessionalInfo(@RequestBody Professionals professionalsInfo){
        return ResponseEntity.ok(service.createNewProfessionalInfo(professionalsInfo));
    }
}
