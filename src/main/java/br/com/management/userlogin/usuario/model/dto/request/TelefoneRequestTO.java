package br.com.management.userlogin.usuario.model.dto.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TelefoneRequestTO implements Serializable {

    private String number;
    private String ddd;
}
