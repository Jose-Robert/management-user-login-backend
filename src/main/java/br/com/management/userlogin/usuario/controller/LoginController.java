package br.com.management.userlogin.usuario.controller;

import br.com.management.userlogin.usuario.model.dto.request.LoginRequestTO;
import br.com.management.userlogin.usuario.model.dto.response.UsuarioResponseTO;
import br.com.management.userlogin.usuario.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseTO> cadastrar(@RequestBody LoginRequestTO requestTO, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(requestTO, request));
    }
}
