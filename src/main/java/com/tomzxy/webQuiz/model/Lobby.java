package com.tomzxy.webQuiz.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "lobbies")
public class Lobby extends BaseEntity {
    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "total_user")
    int total_user;

    String code_invite;

    String user_create_name;

    @ManyToMany
    @JoinTable(name = "group_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> users;



    @OneToMany(mappedBy = "group" ,cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Notifications> notifications;


}
