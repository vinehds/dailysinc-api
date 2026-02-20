package com.vinehds.dailysinc.controller.dto;

import com.vinehds.dailysinc.validation.annotation.ValidEmail;
import jakarta.annotation.Nullable;

public record UpdateMeRequestDTO(
        String name,
        @ValidEmail String email,
        String password) {

}

