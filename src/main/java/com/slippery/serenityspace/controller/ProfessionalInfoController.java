package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.ProfessionalInfoDto;
import com.slippery.serenityspace.models.Professionals;
import com.slippery.serenityspace.services.ProfessionalsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/professionalInfo")
public class ProfessionalInfoController{
    private final ProfessionalsService service;

    public ProfessionalInfoController(ProfessionalsService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<ProfessionalInfoDto> createNewProfessionalInfo(@RequestBody Professionals professionalsInfo){
        return ResponseEntity.ok(service.createNewProfessionalInfo(professionalsInfo));
    }
    @PutMapping("/update-info")
    public ResponseEntity<ProfessionalInfoDto> updateProfessionalInfo(@RequestBody Professionals professionalsInfo,@RequestParam Long infoId) {
        return ResponseEntity.ok(service.updateProfessionalInfo(professionalsInfo, infoId));

    }
    @GetMapping("/{infoId}/find")
    public ResponseEntity<ProfessionalInfoDto> findProfessionalInfoById(@PathVariable Long infoId) {
        return ResponseEntity.ok(service.findProfessionalInfoById(infoId));
    }
    @DeleteMapping("/{infoId}/delete")
    public ResponseEntity<ProfessionalInfoDto> deleteProfessionalInfoById(@PathVariable Long infoId) {
        return ResponseEntity.ok(service.deleteProfessionalInfoById(infoId));
    }
    @GetMapping("/get-all")
    public ResponseEntity<ProfessionalInfoDto> findAllProfessionalInfos() {
        return ResponseEntity.ok(service.findAllProfessionalInfos());
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<ProfessionalInfoDto> deleteAllProfessionalInfos() {
        return ResponseEntity.ok(service.deleteAllProfessionalInfos());
    }
}
