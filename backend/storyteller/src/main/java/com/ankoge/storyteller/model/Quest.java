package com.ankoge.storyteller.model;

import com.ankoge.storyteller.model.type.AreaDirection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Npc giverNpc;

    @ManyToOne
    private Story storyName;
}
