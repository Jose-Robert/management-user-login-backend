package br.com.management.userlogin.usuario.controller;

import br.com.management.userlogin.usuario.model.dto.request.UsuarioRequestTO;
import br.com.management.userlogin.usuario.model.dto.response.UsuarioResponseTO;
import br.com.management.userlogin.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseTO> cadastrar(@RequestBody UsuarioRequestTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(request));
    }

    @GetMapping(value = "/{id}/find-usuario")
    public ResponseEntity<UsuarioResponseTO> buscar(@PathVariable(name = "id") String id, HttpServletRequest servletRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findUsuario(id, servletRequest));
    }
}
