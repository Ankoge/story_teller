package com.ankoge.storyteller.controller;

import com.ankoge.storyteller.controller.dto.LocationDto;
import com.ankoge.storyteller.model.Location;
import com.ankoge.storyteller.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/all-names")
    public @ResponseBody List<String> allLocationNames() {
        return locationService.getLocationNames();
    }

    @GetMapping("/{storyName}/all-names/to-lower-case")
    public @ResponseBody List<String> allLocationNamesInLowerCase(@PathVariable String storyName) {
        return locationService.getLocationNamesByStoryName(storyName).stream()
                .map(locationName -> locationName.toLowerCase()
                        .replaceAll(" ", ""))
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveLocation(@RequestBody LocationDto locationDto){
        return locationService.saveLocationFromDto(locationDto);
    }
}
