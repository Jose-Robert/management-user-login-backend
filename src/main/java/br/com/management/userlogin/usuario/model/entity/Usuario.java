package br.com.management.userlogin.usuario.model.entity;

import br.com.management.userlogin.shared.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private List<Telefone> phones;

    @Column(name = "token")
    private String token;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
    }
}
