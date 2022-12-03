package com.ankoge.storyteller.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestDto {
    private String name;
    private String description;
    private String giverNpc;
    private String location;
}
