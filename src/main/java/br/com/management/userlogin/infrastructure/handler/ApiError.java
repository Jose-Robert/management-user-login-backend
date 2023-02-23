package br.com.management.userlogin.infrastructure.handler;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable {

    private String mensagem;

}
