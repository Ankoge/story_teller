package com.ankoge.storyteller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Story {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long storyNumber;

    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToOne
    private StoryTeller storyTeller;

}
