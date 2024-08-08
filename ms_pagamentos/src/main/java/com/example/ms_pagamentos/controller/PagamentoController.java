package com.example.ms_pagamentos.controller;

import com.example.ms_pagamentos.exception.LimiteCartaoException;
import com.example.ms_pagamentos.exception.PagamentoException;
import com.example.ms_pagamentos.model.records.PagamentoRequest;
import com.example.ms_pagamentos.model.records.PagamentoResponse;
import com.example.ms_pagamentos.model.records.StatusPagamentoResponse;
import com.example.ms_pagamentos.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @Operation(description = "Realiza o cadastro do pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao cadastrar pagamento."),
            @ApiResponse(responseCode = "401", description = "Erro ao autorizar acesso."),
            @ApiResponse(responseCode = "402", description = "Erro ao efetuar o pagamento. Limite do cartão excedido!"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar pagamento")
    })
    @PostMapping(value = "/pagamentos")
    public ResponseEntity<PagamentoResponse> registraPagamento (@RequestBody @Valid PagamentoRequest request,
                                                                @RequestHeader("Authorization") String token )
            throws PagamentoException, LimiteCartaoException {

        PagamentoResponse response = service.registrarPagamento(request,token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(response.getCpf()).toUri();

        return ResponseEntity.ok(response);
    }

    @Operation(description = "Consulta status do pagamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna status do pagamento"),
            @ApiResponse(responseCode = "401", description = "Erro ao autorizar acesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar pagamento")
    })
    @GetMapping("/pagamentos/cliente/{cpf}")
    public Page<StatusPagamentoResponse> listaPorId(@PathVariable String cpf,
                                                    @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                    @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho)
            throws PagamentoException {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        Page<StatusPagamentoResponse> paginas = service.retornaStatusPagamento(pageRequest, cpf);
        return paginas;
    }

}
