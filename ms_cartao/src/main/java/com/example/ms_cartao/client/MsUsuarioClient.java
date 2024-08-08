package com.example.ms_cartao.client;

import com.example.ms_cartao.model.records.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MsUsuarioClient", url = "${ms.usuario.url}")
public interface MsUsuarioClient {

    @GetMapping(value = "/{username}")
    UsuarioResponse validaUsuario(@PathVariable String username);

}
