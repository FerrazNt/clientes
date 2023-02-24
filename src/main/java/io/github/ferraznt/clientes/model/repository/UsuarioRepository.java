package io.github.ferraznt.clientes.model.repository;


import io.github.ferraznt.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
