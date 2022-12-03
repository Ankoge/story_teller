package com.ankoge.storyteller.service;

import com.ankoge.storyteller.controller.dto.NpcDto;
import com.ankoge.storyteller.model.Npc;
import com.ankoge.storyteller.model.type.NpcType;
import com.ankoge.storyteller.service.repository.NpcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NpcService {
    private final NpcRepository npcRepository;
    private final LocationService locationService;

    @Autowired
    public NpcService(NpcRepository npcRepository, LocationService locationService) {
        this.npcRepository = npcRepository;
        this.locationService = locationService;
    }

    public List<Npc> getNpcs() {return npcRepository.findAll();}

    public Optional<Npc> getNpc(Long id) {return npcRepository.findById(id);}

    public Npc save(Npc npc) {
        return npcRepository.save(npc);
    }

    public List<String> getNpcNames() {
        return npcRepository.getAllNpcNames();
    }

    public Npc saveNpcFromDto(NpcDto npcDto) {
        Npc npc = Npc.builder()
                .name(npcDto.getName())
                .story(npcDto.getStory())
                .location(locationService.findByName(npcDto.getLocation()))
                .behavior(npcDto.getBehavior())
                .appearance(npcDto.getAppearance())
                .type(npcDto.getType().equals("") ? null : NpcType.valueOf(npcDto.getType()))
                .build();
        return npcRepository.save(npc);
    }

    public Npc findByName(String name) {
        return npcRepository.findByName(name);
    }
}
