package br.com.management.userlogin.infrastructure.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidatorUtils {

    public static boolean verificarEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
