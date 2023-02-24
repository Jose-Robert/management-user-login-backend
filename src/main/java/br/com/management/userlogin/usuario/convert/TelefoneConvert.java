package br.com.management.userlogin.usuario.convert;

import br.com.management.userlogin.usuario.model.dto.request.TelefoneRequestTO;
import br.com.management.userlogin.usuario.model.entity.Telefone;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static br.com.management.userlogin.infrastructure.utils.StringUtils.removeCaracteresEspeciaisTelefone;

@Component
@RequiredArgsConstructor
public class TelefoneConvert {

    private final ModelMapper mapper;

    public Telefone convertToEntity(TelefoneRequestTO request) {
        request = TelefoneRequestTO.builder()
                .ddd(removeCaracteresEspeciaisTelefone(request.getDdd()))
                .number(removeCaracteresEspeciaisTelefone(request.getNumber()))
                .build();;
        return mapper.map(request, Telefone.class);
    }
}
