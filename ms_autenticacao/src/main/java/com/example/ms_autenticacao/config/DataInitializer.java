package com.example.ms_autenticacao.config;

import com.example.ms_autenticacao.model.Usuario;
import com.example.ms_autenticacao.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(IUsuarioRepository repository) {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            UUID uuid = UUID.randomUUID();
            Usuario usuario = new Usuario();
            usuario.setId(uuid);
            usuario.setUsername("adj2");
            usuario.setPassword(passwordEncoder.encode("adj@1234"));
            repository.save(usuario);
        };
    }

}
