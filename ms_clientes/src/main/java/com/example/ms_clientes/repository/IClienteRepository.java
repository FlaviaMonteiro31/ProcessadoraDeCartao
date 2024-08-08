package com.example.ms_clientes.repository;

import com.example.ms_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByCpf(String cpf);

    @Query("SELECT i FROM Cliente i WHERE i.cpf = :cpf")
    Cliente findClienteByCpf(@Param("cpf") String cpf);
}
