package com.example.ms_cartao.controller;

import com.example.ms_cartao.exception.CartaoException;
import com.example.ms_cartao.exception.LimiteCartaoException;
import com.example.ms_cartao.model.records.CartaoRequest;
import com.example.ms_cartao.model.records.CartaoResponse;
import com.example.ms_cartao.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @Operation(description = "Solicitação para gerar cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao gerar solicitacao de cartão."),
            @ApiResponse(responseCode = "401", description = "Erro ao autorizar acesso."),
            @ApiResponse(responseCode = "403", description = "Numero máximo de cartões atigindo."),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar cartão.")
    })
    @PostMapping(value = "/cartao")
    public ResponseEntity<CartaoResponse> solicitaCartao (@RequestBody @Valid CartaoRequest request, @RequestHeader("Authorization") String token)
            throws CartaoException, LimiteCartaoException, ParseException {

        CartaoResponse response = service.inserirCartao(request, token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(response.getCpf()).toUri();

        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/cartao/{numeroCartao}")
    public ResponseEntity<CartaoResponse> buscarPorId(@PathVariable String numeroCartao) throws CartaoException {
        return ResponseEntity.ok(service.buscarPorNumeroCartao(numeroCartao));
    }

}
