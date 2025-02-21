package com.slippery.serenityspace.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.serenityspace.models.Journal;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalDto {
    private String message;
    private Integer statusCode;
    private Journal journalItem;
    private List<Journal> journalItems;

}
