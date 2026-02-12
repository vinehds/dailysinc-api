package com.vinehds.dailysinc.controller.dto;

import com.vinehds.dailysinc.model.entities.User;
import com.vinehds.dailysinc.validation.annotation.ValidEmail;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(Long id, String name, @NotBlank @ValidEmail String email) {

    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        return user;
    }

}
