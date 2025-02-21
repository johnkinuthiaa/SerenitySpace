package com.slippery.serenityspace.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "id should not be blank")
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
}
