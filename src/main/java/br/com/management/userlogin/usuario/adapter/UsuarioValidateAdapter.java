package br.com.management.userlogin.usuario.adapter;

import br.com.management.userlogin.infrastructure.exception.EmailAlreadyInUseException;
import br.com.management.userlogin.infrastructure.exception.EmailInvalidException;
import br.com.management.userlogin.usuario.model.dto.request.UsuarioRequestTO;
import br.com.management.userlogin.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.management.userlogin.infrastructure.utils.ValidatorUtils.verificarEmail;

@Component
@RequiredArgsConstructor
public class UsuarioValidateAdapter {

    private final UsuarioRepository repository;

    public void validate(UsuarioRequestTO request) {
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
    }
}
