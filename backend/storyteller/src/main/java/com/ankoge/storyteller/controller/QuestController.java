package com.ankoge.storyteller.controller;

import com.ankoge.storyteller.controller.dto.QuestDto;
import com.ankoge.storyteller.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping("/all-names/to-lower-case")
    public @ResponseBody List<String> allNpcNamesToLowerCase() {
        return questService.getQuestNames().stream()
                .map(quest -> quest.toLowerCase()
                        .replaceAll(" ", ""))
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveQuest(@RequestBody QuestDto questDto) {
        questService.saveQuestFromDto(questDto);
        return new ResponseEntity<>("Quest is saved", HttpStatus.OK);
    }
}
