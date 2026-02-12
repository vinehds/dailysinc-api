package com.vinehds.dailysinc.model.enums;

import lombok.Getter;

@Getter
public enum Seniority {

    INTERN(1), TRAINEE(2), JUNIOR(3), PLENO(4), SENIOR(5), SPECIALIST(6);

    private final int code;

    Seniority(int code) {
        this.code = code;
    }

    public static Seniority valueOf(int code) {
        for (Seniority roles : Seniority.values()) {
            if (roles.getCode() == code) {
                return roles;
            }
        }
        throw new IllegalArgumentException("Invalid Role code: " + code);
    }
}