package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.EmergencyResourceDto;
import com.slippery.serenityspace.models.EmergencyResources;
import com.slippery.serenityspace.services.EmergencyResourceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emergency-resources")
public class EmergencyServicesController {
    private final EmergencyResourceService service;

    public EmergencyServicesController(EmergencyResourceService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<EmergencyResourceDto> createNewResource(@Valid @RequestBody EmergencyResources emergencyResources) {
        return ResponseEntity.ok(service.createNewEmergencyResource(emergencyResources));
    }

    @GetMapping("/all")
    public ResponseEntity<EmergencyResourceDto> getAllResources() {
        return ResponseEntity.ok(service.findAllEmergencyResources());
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<EmergencyResourceDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findEmergencyResourceById(id));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<EmergencyResourceDto> deleteAll() {
        return ResponseEntity.ok(service.deleteAllEmergencyResources());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<EmergencyResourceDto> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteEmergencyResourceById(id));
    }
}
