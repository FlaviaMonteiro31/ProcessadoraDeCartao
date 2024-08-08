package com.example.ms_autenticacao.model.records;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoRequest {

    @NotBlank
    private String usuario;
    @NotBlank
    private String senha;

}
