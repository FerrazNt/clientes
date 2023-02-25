package io.github.ferraznt.clientes.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name="login")
    @Email(message = "{invalid.campo.mail}")
    @NotEmpty(message = "{not_null.campo.login}")
    private String username;

    @Column(name = "senha")
    @NotEmpty(message = "{not_null.campo.senha}")
    private String password;

}
