package io.github.ferraznt.clientes.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_servico")
    private Integer id;

    @Column(nullable = false, length = 250)
    private String descricao;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;


}
