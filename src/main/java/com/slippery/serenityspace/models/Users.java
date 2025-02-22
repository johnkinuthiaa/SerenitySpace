package com.slippery.serenityspace.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "username should not be blank")
    private String username;
    @NotBlank(message = "password should not be blank")
    private String password;
    @NotBlank(message = "email should not be blank")
    private String email;
    private String communityAnonymousUsername;
    private byte[] profilePhoto;
    private String role;
    private LocalDateTime createdOn;
    @OneToMany
    private List<Journal> userJournals;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Mood> userMoods;
}
