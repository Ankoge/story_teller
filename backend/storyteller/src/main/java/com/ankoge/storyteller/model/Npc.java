package com.ankoge.storyteller.model;


import com.ankoge.storyteller.model.type.NpcType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Npc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(unique = true)
    private String name;

    private NpcType type;

    private String appearance;

    private String behavior;

    private String story;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Story storyName;
}
