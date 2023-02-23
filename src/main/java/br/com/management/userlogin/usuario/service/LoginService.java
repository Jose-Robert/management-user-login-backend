package br.com.management.userlogin.usuario.service;

import br.com.management.userlogin.usuario.model.dto.request.LoginRequestTO;
import br.com.management.userlogin.usuario.model.dto.response.UsuarioResponseTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    UsuarioResponseTO login(LoginRequestTO requestTO, HttpServletRequest request);
}
