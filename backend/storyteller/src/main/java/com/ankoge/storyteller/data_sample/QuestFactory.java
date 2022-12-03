package com.ankoge.storyteller.data_sample;

import com.ankoge.storyteller.model.Location;
import com.ankoge.storyteller.model.Npc;
import com.ankoge.storyteller.model.Quest;
import com.ankoge.storyteller.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestFactory {
    private final QuestService questService;

    @Autowired
    public QuestFactory(QuestService questService) {
        this.questService = questService;
    }

    public void initialize(List<Npc> npcs, List<Location> locations) {
        questService.save(Quest.builder()
                .name("try")
                .location(locations.get(0))
                .giverNpc(npcs.get(0))
                .build());
        questService.save(Quest.builder()
                .name("try2")
                .location(locations.get(1))
                .giverNpc(npcs.get(1))
                .build());
    }
}
