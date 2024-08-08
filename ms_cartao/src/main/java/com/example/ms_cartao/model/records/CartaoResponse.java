package com.example.ms_cartao.model.records;

import com.example.ms_cartao.model.Cartao;
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

    public CartaoResponse(Cartao cartao){
        this.cartaoId = cartao.getCartaoId();
        this.cpf = cartao.getCpf();
        this.limite = cartao.getLimite();
        this.numero = cartao.getNumero();
        this.data_validade = cartao.getData_validade();
        this.cvv = cartao.getCvv();
    }

}
