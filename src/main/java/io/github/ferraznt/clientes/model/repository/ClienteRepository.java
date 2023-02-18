package io.github.ferraznt.clientes.model.repository;

import io.github.ferraznt.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
