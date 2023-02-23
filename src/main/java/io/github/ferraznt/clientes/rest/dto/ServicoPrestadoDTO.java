package io.github.ferraznt.clientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoPrestadoDTO {

    @NotNull(message = "{not_null.campo.descricao}")
    private String descricao;

    @NotNull(message = "{not_null.campo.valor}")
    private String valor;

    @NotNull(message = "{not_null.campo.data-servico}")
    private String dataServico;

    @NotNull(message = "{not_null.campo.id-cliente}")
    private Integer idCliente;
}
