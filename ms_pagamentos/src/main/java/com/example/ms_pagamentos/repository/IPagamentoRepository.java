package com.example.ms_pagamentos.repository;

import com.example.ms_pagamentos.model.Pagamento;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPagamentoRepository extends JpaRepository<Pagamento, UUID> {

    Optional<Pagamento> findByCpf(String cpf);
    @Query("SELECT i FROM Pagamento i WHERE i.chavePagamento = :id")
    Pagamento findPagamentoById(@Param("id") UUID id);
    @Query("SELECT i FROM Pagamento i WHERE i.cpf = :cpf")
    Page<Pagamento> findAllByCpf(PageRequest pagina, @Param("cpf") String cpf);

    @Query("SELECT i FROM Pagamento i WHERE i.cpf = :cpf")
    Page<Pagamento> somacompras(PageRequest pagina, @Param("cpf") String cpf);
    Optional<Pagamento> findByCpf(CPF cpf);

}
