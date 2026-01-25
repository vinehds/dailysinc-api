package com.vinehds.dailysinc.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "team")
    private List<User> members = new ArrayList<>();


    public void removeMember(User user) {
        members.remove(user);
        user.setTeam(null);
    }

    public void clearMembers() {
        for (User user : new ArrayList<>(this.members)) {
            removeMember(user);
        }
    }
}
