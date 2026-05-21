/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.service;

import com.bidding.system.frontend.model.UserRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Aluno
 */
@Service
public class AuthService {
    private RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:9000";
    
    public String logar(UserRequestDTO user){
        HttpEntity<UserRequestDTO> body = new HttpEntity<>(user); 
        return restTemplate.exchange
                (BASE_URL + "/auth/logar", 
                HttpMethod.POST,
                body,
                String.class).getBody();
        
    }
    
}
