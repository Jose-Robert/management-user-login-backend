package br.com.management.userlogin.usuario.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "Telefone")
public class Telefone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "ddd")
    private String ddd;

    @ManyToOne
    @JoinColumn(name="fk_usuario")
    private Usuario usuario;

    public Telefone(Usuario usuario) {
        this.usuario = usuario;
    }
}
