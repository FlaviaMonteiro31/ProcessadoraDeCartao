package com.example.ms_pagamentos.client;

import com.example.ms_pagamentos.model.records.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MsUsuarioClient", url = "${ms.usuario.url}")
public interface MsUsuarioClient {

    @GetMapping(value = "/{username}")
    UsuarioResponse validaUsuario(@PathVariable String username);

}
