package com.vinehds.dailysinc.controller.dto;

import com.vinehds.dailysinc.model.entities.Team;
import com.vinehds.dailysinc.model.entities.User;
import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.Seniority;
import com.vinehds.dailysinc.model.enums.UserRole;
import com.vinehds.dailysinc.validation.annotation.ValidEmail;
import jakarta.validation.constraints.NotBlank;


public record UserDTO(
        Long id,
        String name,
        @NotBlank @ValidEmail String email,
        UserRole role,
        Department department,
        Seniority seniority,
        Boolean active,
        Long teamId) {

    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getDepartment(),
                user.getSeniority(),
                user.getActive(),
                user.getTeam() == null ? null : user.getTeam().getId());
    }

    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setRole(this.role);
        user.setDepartment(this.department);
        user.setSeniority(this.seniority);
        user.setActive(this.active);

        Team team = null;
        if(this.teamId != null) {
            team = new Team();
            team.setId(this.teamId);
        }

        user.setTeam(team);
        return user;
    }

}
