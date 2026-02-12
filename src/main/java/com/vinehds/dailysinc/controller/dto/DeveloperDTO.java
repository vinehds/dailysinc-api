package com.vinehds.dailysinc.controller.dto;


import com.vinehds.dailysinc.model.entities.Developer;
import com.vinehds.dailysinc.model.entities.Team;
import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.Seniority;
import jakarta.validation.constraints.NotNull;

public record DeveloperDTO(Long id, @NotNull Long teamId, Department department, Seniority seniority, Boolean active, String name) {
    public static DeveloperDTO fromEntity(Developer dev) {
        return new DeveloperDTO(
                dev.getId(),
                dev.getTeam().getId(),
                dev.getDepartment(),
                dev.getSeniority(),
                dev.getActive(),
                dev.getName()
        );
    }

    public Developer toEntity() {
        Developer developer = new Developer();
        developer.setId(this.id);
        developer.setName(this.name);
        developer.setSeniority(this.seniority);
        developer.setActive(this.active);
        developer.setDepartment(this.department);

        Team team = new Team();
        team.setId(this.teamId);

        developer.setTeam(team);
        return developer;
    }
}
