package br.com.management.userlogin.usuario.model.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UsuarioResponseTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private List<TelefoneResponseTO> phones;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
}
