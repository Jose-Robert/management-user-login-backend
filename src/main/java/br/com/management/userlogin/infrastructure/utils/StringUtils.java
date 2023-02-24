package br.com.management.userlogin.infrastructure.utils;

public class StringUtils {

    public static final String REGEX = "[^\\d ]";

    public static String removeCaracteresEspeciaisTelefone(String telefone) {
        var tel = telefone.replaceAll(REGEX, "");
        return tel.replace(" ", "");
    }
}
