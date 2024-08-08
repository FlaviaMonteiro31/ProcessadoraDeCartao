package com.example.ms_autenticacao.model.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest
{
    @NotBlank
    private String usuario;
    @NotBlank
    private String senha;
    @CPF
    private String cpf;
    @NotNull
    private String nome;
    @Email
    private String email;
    @Pattern(regexp = "\\+55\\s\\d{2}\\s\\d{5}-\\d{4}", message = "O número de telefone deve seguir o padrão +55 11 91234-5678")
    private String telefone;
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve seguir o formato 12345-678")
    private String cep;
}
