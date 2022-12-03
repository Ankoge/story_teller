package com.ankoge.storyteller.service.repository;

import com.ankoge.storyteller.model.Location;
import com.ankoge.storyteller.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT name FROM Location")
    List<String> getAllNames();

    Location findByName(String location);

    //"SELECT s.name FROM Story s WHERE s.storyTeller = ?1"
    @Query("SELECT l.name FROM Location l WHERE l.storyName = ?1")
    List<String> getAllNamesByStoryName(Story story);
}
