package io.github.ferraznt.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 250)
    @NotEmpty(message = "{not_null.campo.nome}")
    private String nome;

    @Column(name = "cpf", length = 11, unique = true)
    @NotEmpty(message = "{not_null.campo.cpf}")
    @CPF(message = "{invalid.campo.cpf}")
    private String cpf;

    @Column(name = "dt_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
