package com.ankoge.storyteller.service.repository;

import com.ankoge.storyteller.model.Story;
import com.ankoge.storyteller.model.StoryTeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query("SELECT s.name FROM Story s WHERE s.storyTeller = ?1")
    List<String> findAllNames(StoryTeller storyTeller);

    Optional<Story> findStoryByName(String storyName);
}
