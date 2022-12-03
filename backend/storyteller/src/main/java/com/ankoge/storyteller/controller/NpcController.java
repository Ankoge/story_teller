package com.ankoge.storyteller.controller;

import com.ankoge.storyteller.controller.dto.NpcDto;
import com.ankoge.storyteller.model.type.NpcType;
import com.ankoge.storyteller.service.NpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/npc")
public class NpcController {
    private final NpcService npcService;

    @Autowired
    public NpcController(NpcService npcService) {
        this.npcService = npcService;
    }

    @GetMapping("/all-names")
    public @ResponseBody List<String> getAllNpcNames() {
        return npcService.getNpcNames();
    }

    @GetMapping("/all-names/to-lower-case")
    public @ResponseBody List<String> getAllNpcNamesToLowerCase() {
        return npcService.getNpcNames().stream()
                .map(location -> location.toLowerCase()
                        .replaceAll(" ", ""))
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveNpc(@RequestBody NpcDto npcDto) {
        System.out.println(npcDto);
        npcService.saveNpcFromDto(npcDto);
        return new ResponseEntity<>("Npc is saved", HttpStatus.OK);
    }

    @GetMapping("/types")
    public @ResponseBody List<NpcType> getAllNpcType() {
        return Arrays.asList(NpcType.values());
    }
}
