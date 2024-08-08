package com.example.ms_clientes.controller;

import com.example.ms_clientes.exception.ClienteException;
import com.example.ms_clientes.model.records.ClienteRequest;
import com.example.ms_clientes.model.records.ClienteResponse;
import com.example.ms_clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(description = "Realiza o cadastro do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao cadastrar cliente."),
            @ApiResponse(responseCode = "401", description = "Erro ao autorizar acesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar cliente")
    })
    @PostMapping(value = "/cliente")
    public ResponseEntity<ClienteResponse> inserirCliente (@RequestBody @Valid ClienteRequest request)
            throws ClienteException {

        ClienteResponse response = service.inserirCliente(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(response.getCpf()).toUri();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/cliente/{cpf}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable String cpf) throws ClienteException {
        return ResponseEntity.ok(service.buscarPorCpf(cpf));
    }

}
