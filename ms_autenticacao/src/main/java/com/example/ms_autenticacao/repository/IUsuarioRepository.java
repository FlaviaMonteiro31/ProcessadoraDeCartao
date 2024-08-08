package com.example.ms_autenticacao.repository;

import com.example.ms_autenticacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {
    UserDetails findByUsername(String username);

    Optional<Usuario> findUsuarioByUsername(String username);
}
