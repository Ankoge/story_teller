package com.ankoge.storyteller.service;

import com.ankoge.storyteller.controller.dto.LocationDto;
import com.ankoge.storyteller.model.Location;
import com.ankoge.storyteller.model.Story;
import com.ankoge.storyteller.service.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final StoryService storyService;

    @Autowired
    public LocationService(LocationRepository locationRepository, StoryService storyService) {
        this.locationRepository = locationRepository;
        this.storyService = storyService;
    }

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocation(Long id) {
        return locationRepository.findById(id);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<String> getLocationNames() {
        return locationRepository.getAllNames();
    }

    public Location findByName(String name) {
        return locationRepository.findByName(name);
    }

    public List<String> getLocationNamesByStoryName(String storyName) {
        Optional<Story> story = storyService.findStoryByName(storyName);
        if (story.isPresent()) {
            return locationRepository.getAllNamesByStoryName(story.get());
        } else {
            throw new NoResultException("No story with name: " + storyName);
        }
    }

    public ResponseEntity<String> saveLocationFromDto(LocationDto locationDto) {
        Optional<Story> story = storyService.findStoryByName(locationDto.getStoryName());
        if (story.isPresent()) {
            locationRepository.save(Location.builder()
                    .name(locationDto.getName())
                    .description(locationDto.getDescription())
                    .storyName(story.get())
                    .build());
            return new ResponseEntity<>("Location is saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Location without story can not be saved", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
