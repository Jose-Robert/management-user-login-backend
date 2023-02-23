package br.com.management.userlogin.usuario.convert;

import br.com.management.userlogin.usuario.model.dto.request.UsuarioRequestTO;
import br.com.management.userlogin.usuario.model.dto.response.UsuarioResponseTO;
import br.com.management.userlogin.usuario.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioConvert {

    private final ModelMapper mapper;

    public UsuarioResponseTO convertToDTO(Usuario usuario) {
        return mapper.map(usuario, UsuarioResponseTO.class);
    }

    public Usuario convertToEntity(UsuarioRequestTO request) {
        return mapper.map(request, Usuario.class);
    }
}
