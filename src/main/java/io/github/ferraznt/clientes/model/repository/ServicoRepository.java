package io.github.ferraznt.clientes.model.repository;

import io.github.ferraznt.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
