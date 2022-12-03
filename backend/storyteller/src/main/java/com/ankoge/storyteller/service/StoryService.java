package com.ankoge.storyteller.service;

import com.ankoge.storyteller.model.Story;
import com.ankoge.storyteller.model.StoryTeller;
import com.ankoge.storyteller.service.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final StoryTellerService storyTellerService;

    @Autowired
    public StoryService(StoryRepository storyRepository, StoryTellerService storyTellerService) {
        this.storyRepository = storyRepository;
        this.storyTellerService = storyTellerService;
    }

    public List<String> findAllNames(String username) {
        Optional<StoryTeller> storyTeller= storyTellerService.findByUsername(username);
        if(storyTeller.isEmpty()) {
            return new ArrayList<>();
        }
        return storyRepository.findAllNames(storyTeller.get());
    }

    public Optional<Story> findStoryByName(String storyName) {
        return storyRepository.findStoryByName(storyName);
    }
}
