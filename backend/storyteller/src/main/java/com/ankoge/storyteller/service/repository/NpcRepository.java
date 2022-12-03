package com.ankoge.storyteller.service.repository;

import com.ankoge.storyteller.model.Npc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NpcRepository extends JpaRepository<Npc, Long> {

    @Query("SELECT name from Npc")
    List<String> getAllNpcNames();

    Npc findByName(String name);
}
