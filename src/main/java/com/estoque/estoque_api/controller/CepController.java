package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.cep.CepDto;
import com.estoque.estoque_api.service.CepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepservice;
    public CepController(CepService service) { this.cepservice = service; }

    @GetMapping("/{cep}")
    public ResponseEntity<?> get(@PathVariable String cep) {
        try {
            CepDto dto = cepservice.consultar(cep);
            return ResponseEntity.ok(dto);
        } catch (CepService.CepNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of("erro", "Serviço de CEP indisponível"));
        }
    }

}
