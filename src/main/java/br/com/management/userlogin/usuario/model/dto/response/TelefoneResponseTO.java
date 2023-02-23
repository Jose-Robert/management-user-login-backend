package br.com.management.userlogin.usuario.model.dto.response;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TelefoneResponseTO implements Serializable {

    private String number;
    private String ddd;
}
