package br.com.management.userlogin.usuario.convert;

import br.com.management.userlogin.usuario.model.dto.request.TelefoneRequestTO;
import br.com.management.userlogin.usuario.model.dto.request.UsuarioRequestTO;
import br.com.management.userlogin.usuario.model.dto.response.TelefoneResponseTO;
import br.com.management.userlogin.usuario.model.dto.response.UsuarioResponseTO;
import br.com.management.userlogin.usuario.model.entity.Telefone;
import br.com.management.userlogin.usuario.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TelefoneConvert {

    private final ModelMapper mapper;

    public TelefoneResponseTO convertToDTO(Telefone telefone) {
        return mapper.map(telefone, TelefoneResponseTO.class);
    }

    public Telefone convertToEntity(TelefoneRequestTO request) {
        return mapper.map(request, Telefone.class);
    }
}
