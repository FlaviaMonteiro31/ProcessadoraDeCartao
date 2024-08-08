package com.example.ms_cartao.service;

import com.example.ms_cartao.client.MsClientesClient;
import com.example.ms_cartao.exception.CartaoException;
import com.example.ms_cartao.exception.LimiteCartaoException;
import com.example.ms_cartao.model.Cartao;
import com.example.ms_cartao.model.records.CartaoRequest;
import com.example.ms_cartao.model.records.CartaoResponse;
import com.example.ms_cartao.model.records.ClienteResponse;
import com.example.ms_cartao.repository.ICartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class CartaoService {

    @Autowired
    private ICartaoRepository repository;

    @Autowired
    private MsClientesClient msClientesClient;
    public CartaoResponse inserirCartao(CartaoRequest request, String token) throws CartaoException, LimiteCartaoException, ParseException {
        try {
           ClienteResponse cliente = msClientesClient.buscarCliente(request.getCpf(), token);
        } catch (Exception e){
            throw new CartaoException("O CPF informado não foi localizado!");
        }

        Integer totalCartoesPorCPF = repository.buscaTotalCartaoPorCPF(request.getCpf());
        if(totalCartoesPorCPF == 2){
           throw new LimiteCartaoException("Não foi possível processar a solicitação. O número máximo de cartões para seu CPF foi atingido!");
        }

        Integer existeNumeroDeCartao = repository.existeNumeroDeCartao(request.getNumero());
        if(existeNumeroDeCartao > 0 ){
            throw new CartaoException("O número do cartão informado está indisponivel, por gentileza, verifique a numeracao!");
        }

        Cartao cartao = new Cartao();
        cartao.setCpf(request.getCpf());
        cartao.setLimite(request.getLimite());
        cartao.setNumero(request.getNumero());
        cartao.setData_validade(request.getData_validade());
        cartao.setCvv(request.getCvv());


        Cartao sc = repository.save(cartao);
        return new CartaoResponse(sc);
    }

    public CartaoResponse buscarPorNumeroCartao(String numeroCartao) throws CartaoException {
        Cartao cartao = repository.findByNumero(numeroCartao).orElseThrow(() -> new CartaoException("Cartao não encontrado!!"));
        return new CartaoResponse(cartao);
    }

}
