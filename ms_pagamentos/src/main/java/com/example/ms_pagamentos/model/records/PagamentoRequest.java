package com.example.ms_pagamentos.model.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoRequest {

    @NotBlank
    private String cpf;
    @Pattern(regexp = "^[0-9]{16}", message = "Número do cartão em formato inválido")
    private String numero;
    @JsonFormat(pattern = "MM/yy")
    private Date data_validade;
    @Pattern(regexp = "^[0-9]{3}", message = "Número do CVV com formato inválido")
    private String cvv;
    private BigDecimal valor;


}
