package com.ankoge.storyteller.model;

import com.ankoge.storyteller.secutity.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoryTeller {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = UserRole.class)
    private Set<UserRole> roles;

}
