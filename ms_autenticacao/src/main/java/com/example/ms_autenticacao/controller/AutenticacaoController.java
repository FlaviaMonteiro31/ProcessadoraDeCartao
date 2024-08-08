package com.example.ms_autenticacao.controller;

import com.example.ms_autenticacao.exception.AutenticacaoException;
import com.example.ms_autenticacao.model.records.*;
import com.example.ms_autenticacao.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AutenticacaoController {

    @Autowired
    private UsuarioService service;

    @Operation(description = "Realiza a autenticacao e login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao gerar para logar."),
            @ApiResponse(responseCode = "401", description = "Erro ao autorizar acesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar cartão.")
    })
    @PostMapping(value = "/autenticacao")
    public ResponseEntity<AutenticacaoResponse> login(@RequestBody @Valid AutenticacaoRequest request) throws AutenticacaoException {
        return ResponseEntity.ok(service.login(request));
    }

    @Operation(description = "Registra o usuario e o cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao cadastrar o usuario e o cliente."),
            @ApiResponse(responseCode = "401", description = "Erro ao autorizar acesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar usuario ou cliente.")
    })
    @PostMapping("/autenticacao/usuario")
    public ResponseEntity<ClienteResponse> register(@RequestBody @Valid UsuarioRequest request) throws AutenticacaoException {
        return ResponseEntity.ok(service.registraUsuario(request));
    }

    @GetMapping(value = "/autenticacao/{username}")
    public ResponseEntity<UsuarioResponse> buscarPorUsername(@PathVariable String username) throws AutenticacaoException {
        return ResponseEntity.ok(service.buscarPorUsername(username));
    }

}
