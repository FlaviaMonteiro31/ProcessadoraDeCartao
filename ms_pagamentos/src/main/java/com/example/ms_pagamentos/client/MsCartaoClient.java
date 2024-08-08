package com.example.ms_pagamentos.client;

import com.example.ms_pagamentos.model.records.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "MsCartaoClient", url = "${ms.cartao.url}")
public interface MsCartaoClient {

    @GetMapping(value = "/{numeroCartao}")
    CartaoResponse buscarCartao(@PathVariable String numeroCartao, @RequestHeader("Authorization") String token);

}
