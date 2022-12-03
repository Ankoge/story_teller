package com.ankoge.storyteller.controller;

import com.ankoge.storyteller.model.Story;
import com.ankoge.storyteller.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/story")
public class StoryController {

    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/all-names")
    public List<String> findAllNames() {
        String username = "admin";
        return storyService.findAllNames(username).stream()
                .map(quest -> quest.toLowerCase()
                        .replaceAll(" ", ""))
                .collect(Collectors.toList());
    }
}
