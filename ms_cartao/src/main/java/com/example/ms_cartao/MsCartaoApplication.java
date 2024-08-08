package com.example.ms_cartao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsCartaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCartaoApplication.class, args);
    }

}
