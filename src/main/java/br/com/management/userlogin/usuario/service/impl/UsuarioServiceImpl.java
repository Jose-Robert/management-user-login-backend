package br.com.management.userlogin.usuario.service.impl;

import br.com.management.userlogin.infrastructure.exception.RecursoNaoEncontradoException;
import br.com.management.userlogin.infrastructure.exception.SessionInvalidException;
import br.com.management.userlogin.infrastructure.exception.TokenHeaderErrorException;
import br.com.management.userlogin.infrastructure.utils.JwtTokenUtil;
import br.com.management.userlogin.usuario.adapter.UsuarioValidateAdapter;
import br.com.management.userlogin.usuario.convert.TelefoneConvert;
import br.com.management.userlogin.usuario.convert.UsuarioConvert;
import br.com.management.userlogin.usuario.model.dto.request.UsuarioRequestTO;
import br.com.management.userlogin.usuario.model.dto.response.UsuarioResponseTO;
import br.com.management.userlogin.usuario.model.entity.Telefone;
import br.com.management.userlogin.usuario.model.entity.Usuario;
import br.com.management.userlogin.usuario.repository.UsuarioRepository;
import br.com.management.userlogin.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioConvert usuarioConvert;
    private final TelefoneConvert telefoneConvert;
    private final JwtTokenUtil jwtToken;
    private final UsuarioValidateAdapter validateAdapter;

    @Override
    public UsuarioResponseTO save(UsuarioRequestTO request) {
        validateAdapter.validate(request);
        List<Telefone> phones = request.getPhones().stream().map(telefoneConvert::convertToEntity).collect(Collectors.toList());
        Usuario usuario = usuarioConvert.convertToEntity(request);
        phones.forEach(telefone -> telefone.setUsuario(usuario));
        usuario.setPhones(phones);
        usuario.setToken(jwtToken.generateToken(usuario));
        usuario.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        usuario.setCreated(LocalDateTime.now());
        return usuarioConvert.convertToDTO(repository.save(usuario));
    }

    @Override
    public UsuarioResponseTO findUsuario(String id, HttpServletRequest servletRequest) {
        var usuario = this.authorizeRequest(id, servletRequest);
        this.validaSession(usuario);
        return usuarioConvert.convertToDTO(usuario);
    }

    private Usuario authorizeRequest(String id, HttpServletRequest servletRequest) {
        var tokenHeader = servletRequest.getHeader("Authorization");
        var usuario = repository.findById(id).orElseThrow(RecursoNaoEncontradoException::new);
        if (StringUtils.isBlank(tokenHeader)) {
            log.info("Não foi possível obter o token JWT");
            throw new TokenHeaderErrorException();
        }

        if (!tokenHeader.equals(usuario.getToken())) {
            log.info("O token JWT enviado no Header não pertence a esse Usuário {}", usuario.getName());
            throw new TokenHeaderErrorException();
        }
        return usuario;
    }

    private void validaSession(Usuario usuario) {
        try {
            var lastLogin = usuario.getLastLogin();
            var duration = Duration.between(lastLogin, LocalDateTime.now());
            if (duration.toMinutes() > 30) {
                throw new SessionInvalidException();
            }
        } catch (NullPointerException exception) {
            log.info(exception.getMessage() + " :Sessão Inválida." );
            throw new SessionInvalidException();
        }
    }
}
