package com.slippery.serenityspace.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mindfulness {
    /*
    * Guided meditation sessions, breathing exercises, and relaxation techniques to help users manage stress and anxiety
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Long duration;
    @Lob
    private String content;
}
