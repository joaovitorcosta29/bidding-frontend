/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.service;

import com.bidding.system.frontend.model.EditalDTO;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Aluno
 */
@Service
public class ApiService {
    private final RestTemplate restTemplate = null;

    private final String BASE_URL = "http://localhost:9000";

    public List<EditalDTO> listarEditais(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<EditalDTO[]> response = restTemplate.exchange(
            BASE_URL + "/api/editais",
            HttpMethod.GET,
            entity,
            EditalDTO[].class
        );

    return Arrays.asList(response.getBody());
    }
}