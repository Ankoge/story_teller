package com.ankoge.storyteller.service;

import com.ankoge.storyteller.controller.dto.QuestDto;
import com.ankoge.storyteller.model.Quest;
import com.ankoge.storyteller.service.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {
    private final QuestRepository questRepository;
    private final LocationService locationService;
    private final NpcService npcService;

    @Autowired
    public QuestService(QuestRepository questRepository, LocationService locationService, NpcService npcService) {
        this.questRepository = questRepository;
        this.locationService = locationService;
        this.npcService = npcService;
    }

    public Optional<Quest> getQuest(Long id) {return questRepository.findById(id);}

    public List<Quest> getQuests() {return questRepository.findAll();}

    public Quest save(Quest quest) {
        return questRepository.save(quest);
    }

    public List<String> getQuestNames() {
        return questRepository.getQuestNames();
    }

    public Quest saveQuestFromDto(QuestDto questDto) {
        Quest quest = Quest.builder()
                .name(questDto.getName())
                .description(questDto.getDescription())
                .location(locationService.findByName(questDto.getLocation()))
                .giverNpc(npcService.findByName(questDto.getGiverNpc()))
                .build();
        return questRepository.save(quest);
    }
}
