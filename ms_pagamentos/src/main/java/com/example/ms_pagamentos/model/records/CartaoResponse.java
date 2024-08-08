package com.example.ms_pagamentos.model.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoResponse {
    private UUID cartaoId;
    private String cpf;
    private BigDecimal limite;
    private String numero;
    @JsonFormat(pattern = "MM/yy")
    private Date data_validade;
    private String cvv;

}
