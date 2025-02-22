package com.slippery.serenityspace.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmergencyResources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name should not be blank")
    private String name;
    @Lob
    private String description;
    @Lob
    private List<String> hotlines;
    private LocalDateTime createdOn =LocalDateTime.now();
}
