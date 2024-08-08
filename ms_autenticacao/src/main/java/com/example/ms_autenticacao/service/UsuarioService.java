package com.example.ms_autenticacao.service;

import com.example.ms_autenticacao.client.MsClientesClient;
import com.example.ms_autenticacao.exception.AutenticacaoException;
import com.example.ms_autenticacao.model.Usuario;
import com.example.ms_autenticacao.model.records.*;
import com.example.ms_autenticacao.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MsClientesClient msClientesClient;

    public AutenticacaoResponse login(AutenticacaoRequest request) throws AutenticacaoException {

        UserDetails possivelUsuario = repository.findByUsername(request.getUsuario());
        if(possivelUsuario == null) {
            throw new AutenticacaoException("Usuário não encontrado na base de dados!");
        }

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getSenha());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((Usuario) auth.getPrincipal());
    }

    public ClienteResponse registraUsuario(UsuarioRequest request) throws AutenticacaoException {
        if(repository.findByUsername(request.getEmail()) != null) throw new AutenticacaoException("O usuário informado já existe na base.");
        ClienteResponse clienteResponse;
            try {
                clienteResponse = msClientesClient.inserirCliente(new ClienteRequest(request.getCpf(), request.getNome(), request.getEmail(),
                        request.getTelefone(), request.getCep()));
            } catch (Exception e){
                throw new AutenticacaoException("Ocorreu um erro ao cadastrar os dados");
            }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Usuario usuario = new Usuario();
        usuario.setId(clienteResponse.getClientId());
        usuario.setUsername(request.getUsuario());
        usuario.setPassword(passwordEncoder.encode(request.getSenha()));
        repository.save(usuario);
        return clienteResponse;
    }

    public UsuarioResponse buscarPorUsername(String username) throws AutenticacaoException {
        Usuario usuario = repository.findUsuarioByUsername(username).orElseThrow(() -> new AutenticacaoException("Usuário não encontrado!!"));
        return new UsuarioResponse(usuario);
    }


}
