package com.example.ms_cartao.model;

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
@Table(name="tb_cartao")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartaoId;
    private String cpf;
    private BigDecimal limite;
    private String numero;
    private Date data_validade;
    private String cvv;
}
