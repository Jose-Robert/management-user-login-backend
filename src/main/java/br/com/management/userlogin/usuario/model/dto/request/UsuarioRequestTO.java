package br.com.management.userlogin.usuario.model.dto.request;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UsuarioRequestTO implements Serializable {

    private String name;
    private String email;
    private String password;
    private List<TelefoneRequestTO> phones;
}
