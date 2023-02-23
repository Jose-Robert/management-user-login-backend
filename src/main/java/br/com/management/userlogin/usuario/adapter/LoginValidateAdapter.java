package br.com.management.userlogin.usuario.adapter;

import br.com.management.userlogin.infrastructure.exception.PasswordInvalidoException;
import br.com.management.userlogin.infrastructure.exception.TokenHeaderErrorException;
import br.com.management.userlogin.usuario.model.dto.request.LoginRequestTO;
import br.com.management.userlogin.usuario.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginValidateAdapter {

    public void validate(LoginRequestTO login, HttpServletRequest request, Usuario usuario) {
        this.verificar(login, request, usuario);
    }

    private void verificar(LoginRequestTO login, HttpServletRequest request, Usuario usuario) {

        var tokenHeader = request.getHeader("Authorization");
        var email = login.getEmail();
        var password = login.getPassword();

        if (StringUtils.isBlank(tokenHeader)) {
            log.info("Não foi possível obter o token JWT");
            throw new TokenHeaderErrorException();
        }

        if (!tokenHeader.equals(usuario.getToken())) {
            log.info("O token JWT enviado no Header não pertence a esse Usuário {}", usuario.getName());
            throw new TokenHeaderErrorException();
        }

        if (!usuario.getEmail().equalsIgnoreCase(email)) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        if (!new BCryptPasswordEncoder().matches(password, usuario.getPassword())){
            throw new PasswordInvalidoException();
        }
    }
}
