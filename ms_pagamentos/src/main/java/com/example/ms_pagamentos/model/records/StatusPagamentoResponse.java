package com.example.ms_pagamentos.model.records;

import com.example.ms_pagamentos.model.Pagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StatusPagamentoResponse {
    //private UUID chave_pagamento;
    //private String cpf;
    //private String numero;
    //private Date data_validade;
    //private String cvv;
    private BigDecimal valor;
    private String descricao;
    private String metodo_pagamento;
    private String status;

    public StatusPagamentoResponse(Pagamento pagamento) {
        //this.chave_pagamento = pagamento.getChavePagamento();
        //this.cpf = pagamento.getCpf();
        //this.numero = pagamento.getNumero();
        //this.data_validade = pagamento.getData_validade();
        //this.cvv = pagamento.getCvv();
        this.valor = pagamento.getValor();
        this.descricao = pagamento.getDescricao();
        this.metodo_pagamento = pagamento.getMetodo_pagamento();
        this.status = pagamento.getStatus();
    }
}
