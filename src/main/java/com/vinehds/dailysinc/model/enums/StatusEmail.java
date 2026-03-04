package com.vinehds.dailysinc.model.enums;

import lombok.Getter;

@Getter
public enum StatusEmail {

    PROCESSING(1), SENT(2), ERROR(3);

    private final int code;

    StatusEmail(int code) {
        this.code = code;
    }

    public static StatusEmail valueOf(int code) {
        for (StatusEmail roles : StatusEmail.values()) {
            if (roles.getCode() == code) {
                return roles;
            }
        }
        throw new IllegalArgumentException("Invalid Role code: " + code);
    }
}