package br.com.management.userlogin.usuario.model.dto.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class LoginRequestTO implements Serializable {

    private String email;
    private String password;

}
