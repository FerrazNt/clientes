package io.github.ferraznt.clientes.rest;

import io.github.ferraznt.clientes.model.entity.Cliente;
import io.github.ferraznt.clientes.model.entity.ServicoPrestado;
import io.github.ferraznt.clientes.model.repository.ClienteRepository;
import io.github.ferraznt.clientes.model.repository.ServicoPrestadoRepository;
import io.github.ferraznt.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.ferraznt.clientes.util.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/servicos-prestado")
@CrossOrigin("http://localhost:4200")
public class ServicoPrestadorController {

    private final ServicoPrestadoRepository repository;
    private final ClienteRepository clienteRepository;
    private final BigDecimalConverter bigDecimalConverter;

    public ServicoPrestadorController(ServicoPrestadoRepository repository,
                                      ClienteRepository clienteRepository,
                                      BigDecimalConverter bigDecimalConverter) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto){
        LocalDate data = LocalDate.parse(dto.getDataServico(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente =
                    clienteRepository
                            .findById(idCliente)
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST, "Cliente Não Existe."
                            ));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setDateServico(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getValor()));

        return repository.save(servicoPrestado);

    }
}
