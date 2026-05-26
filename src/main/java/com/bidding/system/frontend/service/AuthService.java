/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.service;

import com.bidding.system.frontend.model.EditalDTO;
import com.bidding.system.frontend.model.UserDTO;
import com.bidding.system.frontend.model.UserRequestDTO;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author Aluno
 */
@Service
public class AuthService {
    
    private final RestClient restClient;

    /**
     * Construtor padrão do serviço.
     *
     * Aqui criamos o RestClient apenas uma vez e configuramos a URL base
     * comum para todas as requisições deste serviço.
     */
    public AuthService() {
        this.restClient = RestClient.builder()
                // Define a base URL que será usada em todas as requisições.
                // Depois, cada chamada só precisa informar o caminho relativo.
                .baseUrl("http://localhost:8081/api")
                .build();
    }

    /**
     * Envia as credenciais do usuário para o endpoint de login.
     *
     * @param user objeto DTO contendo email e senha
     * @return token ou resposta de autenticação como String
     */
    public String logar(UserRequestDTO user) {
        return restClient.post()
                // A URL final será "http://localhost:8081/api/auth/logar".
                .uri("/autenticar/logar")
                .body(user)
                .retrieve()
                .body(String.class);
    }
    
    public void registrar(UserDTO user ) {
        user.setRole("FORNECEDOR");
        String retorno = 
            restClient
                .post()
                .uri("/autenticar/registrar")
                .body(user)
                .retrieve()
                .body(String.class);
    }

    public List<EditalDTO> listarEditais(String token) {
        EditalDTO[] editais = restClient.get()
                .uri("/editais")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(EditalDTO[].class);

        return Arrays.asList(editais);
    }
}
