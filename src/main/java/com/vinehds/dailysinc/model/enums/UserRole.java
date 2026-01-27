package com.vinehds.dailysinc.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN(1), TECHLEAD(2), MEMBER(3);

    private final int code;

    UserRole(int code) {
        this.code = code;
    }

    public static UserRole valueOf(int code) {
        for (UserRole roles : UserRole.values()) {
            if (roles.getCode() == code) {
                return roles;
            }
        }
        throw new IllegalArgumentException("Invalid Role code: " + code);
    }
}