package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.lote.LoteDto;
import com.estoque.estoque_api.model.Lote;
import com.estoque.estoque_api.service.LoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lote")
@RequiredArgsConstructor
public class LoteController {

    private final LoteService loteService;
    private final ModelMapper modelMapper;

    @PostMapping
    ResponseEntity<LoteDto> salvarLote(@Valid @RequestBody LoteDto loteDto) {
        Lote lote = modelMapper.map(loteDto, Lote.class);
        loteService.salvar(lote);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
