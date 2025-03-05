package com.tomzxy.webQuiz.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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

    @Column(name = "total_user", nullable = false, columnDefinition = "int default 0")
    int total_user = 0;

    String code_invite;

    String creator_userName;

    @ManyToMany
    @JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> users;

    @OneToMany(mappedBy = "group", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    List<Notifications> notifications;

    @OneToMany(mappedBy = "lobby", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    List<Quiz> quizzes;

}
