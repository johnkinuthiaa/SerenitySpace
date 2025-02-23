package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.WrittenResourcesDto;
import com.slippery.serenityspace.models.WrittenResources;
import com.slippery.serenityspace.services.WrittenResourceService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resources")
public class WrittenResourceController{
    private final WrittenResourceService service;

    public WrittenResourceController(WrittenResourceService service) {
        this.service = service;
    }
    @PostMapping("/create/new")
    public ResponseEntity<WrittenResourcesDto> createNewResource(@RequestBody WrittenResources writtenResources) {
        return ResponseEntity.ok(service.createNewResource(writtenResources));
    }
    @PutMapping("/{resourceId}/update")
    public ResponseEntity<WrittenResourcesDto> updateResource(@RequestBody WrittenResources writtenResources,@PathVariable Long resourceId) {
        return ResponseEntity.ok(service.updateResource(writtenResources, resourceId));
    }
    @GetMapping("/{resourceId}/get")
    public ResponseEntity<WrittenResourcesDto> findResourceById(@PathVariable Long resourceId) {
        return ResponseEntity.ok(service.findResourceById(resourceId));
    }
    @DeleteMapping("/{resourceId}/delete")
    public ResponseEntity<WrittenResourcesDto> deleteResourceById(@PathVariable Long resourceId) {
        return ResponseEntity.ok(service.deleteResourceById(resourceId));
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<WrittenResourcesDto> deleteAllResources() {
        return ResponseEntity.ok(service.deleteAllResources());
    }
    @GetMapping("/get/all")
    public ResponseEntity<WrittenResourcesDto> getAllResources() {
        return ResponseEntity.ok(service.getAllResources());
    }
}
