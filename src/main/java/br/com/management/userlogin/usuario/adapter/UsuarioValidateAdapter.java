package br.com.management.userlogin.usuario.adapter;

import br.com.management.userlogin.infrastructure.exception.*;
import br.com.management.userlogin.infrastructure.utils.ValidatorUtils;
import br.com.management.userlogin.usuario.model.dto.request.UsuarioRequestTO;
import br.com.management.userlogin.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static br.com.management.userlogin.infrastructure.utils.StringUtils.removeCaracteresEspeciaisTelefone;
import static br.com.management.userlogin.infrastructure.utils.ValidatorUtils.verificarEmail;

@Component
@RequiredArgsConstructor
public class UsuarioValidateAdapter {

    private final UsuarioRepository repository;

    public void validate(UsuarioRequestTO request) {
        this.validaObrigatoriedadeDosCampos(request);
        this.verificar(request);
    }

    private void verificar(UsuarioRequestTO request) {
        var email = request.getEmail();

        boolean isEmailValid = verificarEmail(email);
        if (!isEmailValid) {
            throw new EmailInvalidException();
        }

        boolean existsByEmail = repository.existsByEmail(email);
        if (existsByEmail) {
            throw new EmailAlreadyInUseException();
        }

        var password = request.getPassword();
        boolean isPasswordValid = ValidatorUtils.isValid(password);
        if (!isPasswordValid) {
            throw new InvalidPasswordPatternException();
        }
    }

    private void validaObrigatoriedadeDosCampos(UsuarioRequestTO requestTO) {
        var name = requestTO.getName();
        if (StringUtils.isBlank(name)) {
            throw new CampoObrigatorioException("Name");
        }

        requestTO.getPhones().forEach(telefoneRequestTO -> {
            String number = removeCaracteresEspeciaisTelefone(telefoneRequestTO.getNumber());
            if (StringUtils.isBlank(number)) {
                throw new CampoObrigatorioException("Number");
            }

            if (number.length() != 9) {
                throw new CampoTamanhoMaximoException("Number", "9");
            }

            String ddd = removeCaracteresEspeciaisTelefone(telefoneRequestTO.getDdd());
            if (StringUtils.isBlank(ddd)) {
                throw new CampoObrigatorioException("DDD");
            }
        });

        var email = requestTO.getEmail();
        if (StringUtils.isBlank(email)) {
            throw new CampoObrigatorioException("E-mail");
        }

        var password = requestTO.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new CampoObrigatorioException("Password");
        }

    }
}
