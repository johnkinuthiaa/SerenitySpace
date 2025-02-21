package com.slippery.serenityspace.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.serenityspace.models.EmergencyResources;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmergencyResourceDto {
    private String message;
    private Integer statusCode;
    private EmergencyResources emergencyResource;
    private List<EmergencyResources> emergencyResources;

}
