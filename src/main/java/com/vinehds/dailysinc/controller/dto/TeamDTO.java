package com.vinehds.dailysinc.controller.dto;

import com.vinehds.dailysinc.model.entities.Developer;
import com.vinehds.dailysinc.model.entities.Team;

import java.util.List;

public record TeamDTO(Long id, String name, String description) {

    public static TeamDTO fromEntity(Team team) {
        return new TeamDTO(team.getId(), team.getName(), team.getDescription());
    }

    public Team toEntity() {
        Team team = new Team();
        team.setName(this.name);
        team.setDescription(this.description);
        return team;
    }
}
