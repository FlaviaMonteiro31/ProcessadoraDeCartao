package com.example.ms_cartao.client;

import com.example.ms_cartao.model.records.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "MsClientesClient", url = "${ms.clientes.url}")
public interface MsClientesClient {

    @GetMapping(value = "/{cpf}")
    ClienteResponse buscarCliente(@PathVariable String cpf, @RequestHeader("Authorization") String token);

}
