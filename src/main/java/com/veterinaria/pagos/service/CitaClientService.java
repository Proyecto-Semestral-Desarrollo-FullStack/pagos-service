package com.veterinaria.pagos.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CitaClientService {

    private final WebClient citasWebClient;

    public CitaClientService(@Qualifier("citasWebClient") WebClient citasWebClient) {
        this.citasWebClient = citasWebClient;
    }

    public boolean existeCita(Long citaId) {
        try {
            Boolean existe = citasWebClient.get()
                    .uri("/api/citas/existe/{id}", citaId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            return Boolean.TRUE.equals(existe);
        } catch (Exception e) {
            System.err.println("❌ Error conectando con citas-service: " + e.getMessage());
            return false;
        }
    }
}