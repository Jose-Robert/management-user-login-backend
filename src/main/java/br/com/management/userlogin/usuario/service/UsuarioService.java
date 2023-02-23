package br.com.management.userlogin.usuario.service;

import br.com.management.userlogin.usuario.model.dto.request.UsuarioRequestTO;
import br.com.management.userlogin.usuario.model.dto.response.UsuarioResponseTO;

import javax.servlet.http.HttpServletRequest;

public interface UsuarioService {

    UsuarioResponseTO save(UsuarioRequestTO request);
    UsuarioResponseTO findUsuario(String id, HttpServletRequest servletRequest);
}
