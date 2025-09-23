package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.cep.CepDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Service
public class CepService {

    private final WebClient client;
    private static final Pattern CEP8 = Pattern.compile("^\\d{8}$");

    public CepService(WebClient viaCepClient) {
        this.client = viaCepClient;
    }

    public CepDto consultar(String cep) {
        String clean = normalizarCep(cep);

        CepDto resp = client.get()
                .uri(uri -> uri.path("/ws/{cep}/json/").build(clean))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, r ->
                        Mono.error(new RuntimeException("ViaCEP indisponível")))
                .bodyToMono(CepDto.class)
                .block();

        if (resp == null) throw new RuntimeException("Resposta vazia do ViaCEP");
        if (Boolean.TRUE.equals(resp.getErro()))
            throw new CepNaoEncontradoException(clean);

        return resp;
    }

    private String normalizarCep(String input) {
        if (input == null || input.isBlank())
            throw new IllegalArgumentException("CEP vazio");
        String digits = input.replaceAll("\\D", "");
        if (!CEP8.matcher(digits).matches())
            throw new IllegalArgumentException("CEP inválido: " + input);
        return digits;
    }

    public static class CepNaoEncontradoException extends RuntimeException {
        public CepNaoEncontradoException(String cep) {
            super("CEP não encontrado: " + cep);
        }
    }
}