package com.ankoge.storyteller.service;

import com.ankoge.storyteller.model.StoryTeller;
import com.ankoge.storyteller.service.repository.StoryTellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StoryTellerService implements UserDetailsService {

    private final StoryTellerRepository storyTellerRepository;

    @Autowired
    public StoryTellerService(StoryTellerRepository storyTellerRepository) {
        this.storyTellerRepository = storyTellerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StoryTeller storyTeller;
        Optional<StoryTeller> storyTellerOptional = storyTellerRepository.findByUsername(username);
        if(storyTellerOptional.isEmpty()) {
            System.out.println("ERROR, no user in database with: " + username);
            throw new UsernameNotFoundException("User not found in database");
        }else {
            storyTeller = storyTellerOptional.get();
            System.out.println("User found in database: " + username);
        }
        Collection<SimpleGrantedAuthority> authorizes = new ArrayList<>();
        storyTeller.getRoles()
                .forEach(role ->
                        authorizes.add(new SimpleGrantedAuthority(role.name())));
        return new User(storyTeller.getUsername(), storyTeller.getPassword(), authorizes);
    }


    public Optional<StoryTeller> findById(Long storyTellerId) {
        return storyTellerRepository.findById(storyTellerId);
    }

    public Optional<StoryTeller> findByUsername(String username) {
        return storyTellerRepository.findByUsername(username);
    }
}
