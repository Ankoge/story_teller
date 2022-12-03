package com.ankoge.storyteller.service.repository;

import com.ankoge.storyteller.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

    @Query("SELECT name FROM Quest")
    List<String> getQuestNames();
}
