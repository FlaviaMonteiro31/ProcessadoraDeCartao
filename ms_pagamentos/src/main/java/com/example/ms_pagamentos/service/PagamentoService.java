package com.example.ms_pagamentos.service;

import com.example.ms_pagamentos.client.MsCartaoClient;
import com.example.ms_pagamentos.enums.MeioPagamentoEnum;
import com.example.ms_pagamentos.enums.StatusEnum;
import com.example.ms_pagamentos.exception.LimiteCartaoException;
import com.example.ms_pagamentos.exception.PagamentoException;
import com.example.ms_pagamentos.model.Pagamento;
import com.example.ms_pagamentos.model.records.CartaoResponse;
import com.example.ms_pagamentos.model.records.PagamentoRequest;
import com.example.ms_pagamentos.model.records.PagamentoResponse;
import com.example.ms_pagamentos.model.records.StatusPagamentoResponse;
import com.example.ms_pagamentos.repository.IPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PagamentoService {

    @Autowired
    private IPagamentoRepository repository;

    @Autowired
    private MsCartaoClient msCartaoClient;

    public PagamentoResponse registrarPagamento(PagamentoRequest request, String token) throws PagamentoException, LimiteCartaoException {
        CartaoResponse dadosCartao;

        try {
            dadosCartao = msCartaoClient.buscarCartao(request.getNumero(), token);
        } catch (Exception e){
            throw new PagamentoException("O cartão não foi localizado!");
        }

        if(!dadosCartao.getCpf().equals(request.getCpf())){
            throw new PagamentoException("Não foi possível processar o pagamento! Por gentileza, verifique os dados do cliente.");
        }

        if(!dadosCartao.getCvv().equals(request.getCvv())){
            throw new PagamentoException("Não foi possível processar o pagamento! Por gentileza, verifique o CVV.");
        }

        if(!dadosCartao.getData_validade().equals(request.getData_validade())){
            throw new PagamentoException("Não foi possível processar o pagamento! Por gentileza, verifique a data de validade.");
        }

        BigDecimal limiteCartao = dadosCartao.getLimite();
        BigDecimal valorCompra = request.getValor();
        if(!limiteDisponivelParaCompra(valorCompra, limiteCartao)){
           throw new LimiteCartaoException("Pagamento recusado! O limite do cartão foi excedido.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setCpf(request.getCpf());
        pagamento.setNumero(request.getNumero());
        pagamento.setData_validade(request.getData_validade());
        pagamento.setCvv(request.getCvv());
        pagamento.setValor(request.getValor());
        //if(pagamentoAprovado() == true){
            pagamento.setStatus(StatusEnum.APROVADO.toString());//aprovado
        //} else {
        //    pagamento.setStatus(StatusEnum.RECUSADO.toString());//recusado
        //}
        pagamento.setDescricao("Compra de produtos");
        pagamento.setMetodo_pagamento(MeioPagamentoEnum.CARTAO_CREDITO.toString());

        Pagamento pag = repository.save(pagamento);
        return new PagamentoResponse(pag);
    }

    public static Boolean limiteDisponivelParaCompra(BigDecimal valorCompra, BigDecimal limiteCartao){
        if(valorCompra.compareTo(limiteCartao) == 0) {
           return false;
        }
        if(valorCompra.compareTo(limiteCartao) == 1){
            return false;
        }
        return true;
    }


    public static Boolean pagamentoAprovado(){
        Random random = new Random();
        int aprovado = random.nextInt(1,3);
        System.out.println(aprovado);

        if(aprovado == 2){
            return false;
        }
        return true;
    }
    public Page<StatusPagamentoResponse> retornaStatusPagamento(PageRequest pagina, String cpf) throws PagamentoException {
        Page<Pagamento> pag =  repository.findAllByCpf(pagina, cpf);
        return pag.map(p -> new StatusPagamentoResponse(p));
    }
}
