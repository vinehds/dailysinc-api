package com.vinehds.dailysinc.controller.dto;

import com.vinehds.dailysinc.model.entities.Team;
import com.vinehds.dailysinc.model.entities.User;

public record UserDTO(Long id, String name, String email, Long teamId) {

    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(),
                user.getTeam() != null ? user.getTeam().getId() : null);
    }

    public User toEntity() {
        User user = new User();
        Team team = new Team();
        team.setId(this.teamId);

        user.setName(this.name);
        user.setEmail(this.email);
        user.setTeam(team);

        return user;
    }

}
