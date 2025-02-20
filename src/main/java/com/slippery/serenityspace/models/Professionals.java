package com.slippery.serenityspace.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Professionals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullNames;
    private String location;
    private String address;
    private String email;
    private String gender;
    private String about;
    private String specialization;
    private String institution;
    private String openingTime;
    private String mobileNumber;
    @Lob
    private List<String> socials;
}
