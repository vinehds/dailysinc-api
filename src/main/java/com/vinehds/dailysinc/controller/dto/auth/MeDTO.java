package com.vinehds.dailysinc.controller.dto.auth;

import com.vinehds.dailysinc.model.entities.User;

public record MeDTO(Long id, String name, String email, String role) {
    public static MeDTO fromDeveloper(User user) {
        return new MeDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}