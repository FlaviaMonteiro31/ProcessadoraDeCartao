package com.example.ms_autenticacao.model.records;

import com.example.ms_autenticacao.model.Usuario;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private UUID id;
    private String username;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
    }
}
