package io.github.ferraznt.clientes.service;

import io.github.ferraznt.clientes.exception.UsuarioCadastradoException;
import io.github.ferraznt.clientes.model.entity.Usuario;
import io.github.ferraznt.clientes.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario salvar(Usuario usuario){
        boolean exists = usuarioRepository.existsByUsername(usuario.getUsername());
        if (!exists){
            return usuarioRepository.save(usuario);
        }else{
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

       Usuario usuario = usuarioRepository
                    .findByUsername(username)
            .orElseThrow(
                    () -> new UsernameNotFoundException("Usuário Não Encontrado.")
            );
        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}
