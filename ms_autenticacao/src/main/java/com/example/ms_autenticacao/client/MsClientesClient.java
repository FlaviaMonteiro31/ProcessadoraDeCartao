package com.example.ms_autenticacao.client;

import com.example.ms_autenticacao.model.records.ClienteRequest;
import com.example.ms_autenticacao.model.records.ClienteResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "MsClientesClient", url = "${ms.clientes.url}")
public interface MsClientesClient {

    @PostMapping
    ClienteResponse inserirCliente(@Valid @RequestBody ClienteRequest request);

}
