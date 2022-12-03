package com.ankoge.storyteller.data_sample;

import com.ankoge.storyteller.model.Location;
import com.ankoge.storyteller.model.Npc;
import com.ankoge.storyteller.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class LocationFactory {
    private final LocationService locationService;
    private final NpcFactory npcFactory;
    private final QuestFactory questFactory;


    public LocationFactory(LocationService locationService, NpcFactory npcFactory, QuestFactory questFactory) {
        this.locationService = locationService;
        this.npcFactory = npcFactory;
        this.questFactory = questFactory;
        initialize();
    }

    private void initialize() {
        List<Location> locations = new ArrayList<>();
        locations.add(locationService.saveLocation(Location.builder()
                .name("Dragon's Stand")
                .build()));
        locations.add(locationService.saveLocation(Location.builder()
                .name("Tangled Depths")
                .build()));
        locations.add(locationService.saveLocation(Location.builder()
                .name("Handle Town")
                .build()));
        locations.add(locationService.saveLocation(Location.builder()
                .name("Edge Town")
                .build()));
        List<Npc> npcs = npcFactory.initialize(locations);
        questFactory.initialize(npcs, locations);

    }


}
