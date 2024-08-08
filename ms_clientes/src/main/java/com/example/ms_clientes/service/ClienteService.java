package com.example.ms_clientes.service;

import com.example.ms_clientes.exception.ClienteException;
import com.example.ms_clientes.model.Cliente;
import com.example.ms_clientes.model.records.ClienteRequest;
import com.example.ms_clientes.model.records.ClienteResponse;
import com.example.ms_clientes.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;

    public ClienteResponse inserirCliente(ClienteRequest request) throws ClienteException {

        var existeCliente = repository.findByCpf(request.getCpf());
        if(!existeCliente.isEmpty()){
           throw new ClienteException("O CPF informado já existe!");
        }
        Cliente cliente = new Cliente();
        cliente.setCpf(request.getCpf());
        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setRua(request.getRua());
        cliente.setCidade(request.getCidade());
        cliente.setEstado(request.getEstado());
        cliente.setCep(request.getCep());
        cliente.setPais(request.getPais());
        Cliente sc = repository.save(cliente);
        return new ClienteResponse(sc);
    }

    public ClienteResponse buscarPorCpf(String cpf) throws ClienteException {
        Cliente cliente = repository.findByCpf(cpf).orElseThrow(() -> new ClienteException("Cliente não encontrado!!"));
        return new ClienteResponse(cliente);
    }

}
