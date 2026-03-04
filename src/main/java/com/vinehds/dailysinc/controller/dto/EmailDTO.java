package com.vinehds.dailysinc.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record EmailDTO(
        @NotBlank String ownerRef,
        @Email @NotBlank String emailFrom,
        @Email @NotBlank String emailTo,
        @NotBlank String subject,
        @NotBlank String text) {
}
