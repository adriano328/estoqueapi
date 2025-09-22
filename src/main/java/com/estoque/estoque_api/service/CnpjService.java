package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.cnpj.CnpjResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.regex.Pattern;

@Service
public class CnpjService {

    private final WebClient client;
    private static final Pattern DIGITOS_14 = Pattern.compile("^\\d{14}$");
    private static final Pattern FORMATADO = Pattern.compile("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-?\\d{2}$");

    public CnpjService(WebClient openCnpjClient) {
        this.client = openCnpjClient;
    }

    public CnpjResponse consultar(String cnpj) {
        String path = normalizarCnpj(cnpj); // aceita com/sem pontuação
        return client.get()
                .uri("/{cnpj}", path)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, resp ->
                        Mono.error(new CnpjNaoEncontradoException(path)))
                .onStatus(HttpStatus.TOO_MANY_REQUESTS::equals, resp ->
                        Mono.error(new RateLimitException()))
                .bodyToMono(CnpjResponse.class)
                .retryWhen(
                        Retry
                                .fixedDelay(2, Duration.ofMillis(300))
                                .filter(ex -> ex instanceof RateLimitException)
                )
                .block();
    }

    private String normalizarCnpj(String input) {
        if (input == null || input.isBlank())
            throw new IllegalArgumentException("CNPJ vazio");
        String s = input.trim();
        if (DIGITOS_14.matcher(s).matches()) return s;
        if (FORMATADO.matcher(s).matches()) return s;
        String digits = s.replaceAll("\\D", "");
        if (!DIGITOS_14.matcher(digits).matches())
            throw new IllegalArgumentException("CNPJ inválido: " + input);
        return digits;
    }

    public static class CnpjNaoEncontradoException extends RuntimeException {
        public CnpjNaoEncontradoException(String cnpj) { super("CNPJ não encontrado: " + cnpj); }
    }
    public static class RateLimitException extends RuntimeException {}
}
