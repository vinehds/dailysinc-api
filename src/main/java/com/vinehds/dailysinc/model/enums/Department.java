package com.vinehds.dailysinc.model.enums;

import lombok.Getter;

@Getter
public enum Department {

    WEB_DEVELOPER(1), DESK_DEVELOPER(2), UI_UX(3), OTHER(4);

    private final int code;

    Department(int code) {
        this.code = code;
    }

    public static Department valueOf(int code) {
        for (Department roles : Department.values()) {
            if (roles.getCode() == code) {
                return roles;
            }
        }
        throw new IllegalArgumentException("Invalid Role code: " + code);
    }
}