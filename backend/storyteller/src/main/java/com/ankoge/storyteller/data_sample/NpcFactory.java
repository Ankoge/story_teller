package com.ankoge.storyteller.data_sample;

import com.ankoge.storyteller.model.Location;
import com.ankoge.storyteller.model.Npc;
import com.ankoge.storyteller.service.NpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NpcFactory {
    private final NpcService npcService;

    @Autowired
    public NpcFactory(NpcService npcService) {
        this.npcService = npcService;
    }

    public List<Npc> initialize(List<Location> locations) {
        List<Npc> npcs = new ArrayList<>();

        npcs.add(npcService.save(Npc.builder()
                        .name("Bob")
                        .location(locations.get(2))
                .build()));
        npcs.add(npcService.save(Npc.builder()
                        .name("Jen")
                        .location(locations.get(3))
                .build()));
        return npcs;
    }
}
