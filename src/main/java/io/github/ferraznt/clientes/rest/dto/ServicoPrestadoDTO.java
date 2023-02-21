package io.github.ferraznt.clientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoPrestadoDTO {
    private String descricao;
    private String valor;
    private String dataServico;
    private Integer idCliente;
}
