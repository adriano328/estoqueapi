package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.service.CnpjService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cnpj")
public class CnpjController {
    private final CnpjService service;

    public CnpjController(CnpjService service) {
        this.service = service;
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> get(@PathVariable String cnpj) {
        try {
            return ResponseEntity.ok(service.consultar(cnpj));
        } catch (CnpjService.CnpjNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", e.getMessage()));
        } catch (CnpjService.RateLimitException e) {
            return ResponseEntity.status(429).body(Map.of("erro", "Muitas requisições, tente novamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}