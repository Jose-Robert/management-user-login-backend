package br.com.management.userlogin.infrastructure.utils;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {

    //Requisitos de senha segura
    //A senha deve conter pelo menos um dígito [0-9].
    //A senha deve conter pelo menos um caractere latino minúsculo [az].
    //A senha deve conter pelo menos um caractere latino maiúsculo [AZ].
    //A senha deve conter pelo menos um caractere especial como ! @ # & ( ).
    //A senha deve conter no mínimo 8 caracteres e no máximo 20 caracteres.
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean verificarEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
