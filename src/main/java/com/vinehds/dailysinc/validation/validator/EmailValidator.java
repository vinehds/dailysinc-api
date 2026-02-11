package com.vinehds.dailysinc.validation.validator;

import com.vinehds.dailysinc.validation.annotation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.mail.internet.InternetAddress;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isBlank()) return false;

        try {
            InternetAddress email = new InternetAddress(value);
            email.validate();

            // TODO: if (value.endsWith("@example.com")) return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

