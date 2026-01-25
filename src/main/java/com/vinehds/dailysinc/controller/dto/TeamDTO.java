package com.vinehds.dailysinc.controller.dto;

import com.vinehds.dailysinc.model.entities.Team;

import java.util.List;

public record TeamDTO(Long id, String name, String description, List<UserDTO> users) {

    public static TeamDTO fromEntity(Team team) {
        return new TeamDTO(team.getId(), team.getName(), team.getDescription(), team.getMembers().stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), team.getId())).toList());
    }

    public Team toEntity() {
        Team team = new Team();
        team.setName(this.name);
        team.setDescription(this.description);
        return team;
    }
}
