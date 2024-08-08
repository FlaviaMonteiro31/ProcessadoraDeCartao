package com.example.ms_cartao.repository;

import com.example.ms_cartao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ICartaoRepository extends JpaRepository<Cartao, UUID> {

    Optional<Cartao> findByCpf(String cpf);

    Optional<Cartao> findByNumero(String numero);
    @Query("SELECT Count(i) FROM Cartao i WHERE i.cpf= :cpf")
    Integer buscaTotalCartaoPorCPF(@Param("cpf") String cpf);

    @Query("SELECT Count(i) FROM Cartao i WHERE i.numero = :numero")
    Integer existeNumeroDeCartao(@Param("numero") String numero);
}
