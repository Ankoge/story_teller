package com.ankoge.storyteller.controller.dto;

import com.ankoge.storyteller.model.type.NpcType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcDto {

    private String name;
    private String location;
    private String type;
    private String behavior;
    private String appearance;
    private String story;
}
