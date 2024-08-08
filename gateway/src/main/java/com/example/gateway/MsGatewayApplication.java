package com.example.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class MsGatewayApplication {

    @Value("${host.ms.autenticacao}")
    private String hostMsAutenticacao;

    @Value("${host.ms.pagamentos}")
    private String hostMsPagamentos;

    @Value("${host.ms.cartao}")
    private String hostMsCartao;

    @Value("${host.ms.clientes}")
    private String hostMsClientes;

    public static void main(String[] args) {
        SpringApplication.run(MsGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        // Adiciona logs para depuração
        System.out.println("Autenticacao Host: " + hostMsAutenticacao);
        System.out.println("Pagamentos Host: " + hostMsPagamentos);
        System.out.println("Cartao Host: " + hostMsCartao);
        System.out.println("Clientes Host: " + hostMsClientes);

        return builder
                .routes()
                .route(r -> r.path("/api/autenticacao/**").uri(this.hostMsAutenticacao))
                .route(r -> r.path("/api/pagamentos/**").uri(this.hostMsPagamentos))
                .route(r -> r.path("/api/cartao**").uri(this.hostMsCartao))
                .route(r -> r.path("/api/cliente**").uri(this.hostMsClientes))
                .build();
    }
}
