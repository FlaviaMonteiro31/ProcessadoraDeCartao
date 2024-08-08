package com.example.ms_pagamentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID chavePagamento;
    private String cpf;
    private String numero;
    private Date data_validade;
    private String cvv;
    private BigDecimal valor;
    private String descricao;
    private String metodo_pagamento;
    private String status;

}
