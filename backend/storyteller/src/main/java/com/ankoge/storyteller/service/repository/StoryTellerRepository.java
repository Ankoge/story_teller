package com.ankoge.storyteller.service.repository;

import com.ankoge.storyteller.model.StoryTeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoryTellerRepository extends JpaRepository<StoryTeller, Long> {
    Optional<StoryTeller> findByUsername(String username);
}
