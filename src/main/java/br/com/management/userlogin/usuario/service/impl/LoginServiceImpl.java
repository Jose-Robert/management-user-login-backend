package br.com.management.userlogin.usuario.service.impl;

import br.com.management.userlogin.infrastructure.utils.JwtTokenUtil;
import br.com.management.userlogin.usuario.adapter.LoginValidateAdapter;
import br.com.management.userlogin.usuario.convert.UsuarioConvert;
import br.com.management.userlogin.usuario.model.dto.request.LoginRequestTO;
import br.com.management.userlogin.usuario.model.dto.response.UsuarioResponseTO;
import br.com.management.userlogin.usuario.repository.UsuarioRepository;
import br.com.management.userlogin.usuario.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConvert usuarioConvert;
    private final JwtTokenUtil jwtToken;
    private final LoginValidateAdapter validateAdapter;

    @Override
    public UsuarioResponseTO login(LoginRequestTO requestTO, HttpServletRequest servletRequest) {
        var usuario = usuarioRepository.findByEmail(requestTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " +requestTO.getEmail()));
        validateAdapter.validate(requestTO, servletRequest, usuario);
        usuario.setToken(jwtToken.generateToken(usuario));
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setModified(LocalDateTime.now());
        return usuarioConvert.convertToDTO(usuarioRepository.save(usuario));
    }
}
