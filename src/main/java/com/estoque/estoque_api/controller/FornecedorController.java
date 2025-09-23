package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.fornecedor.FornecedorDto;
import com.estoque.estoque_api.model.Fornecedor;
import com.estoque.estoque_api.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("fornecedor")
@RestController
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final ModelMapper modelMapper;

    @PostMapping()
    ResponseEntity<FornecedorDto> salvarFornecedor(@Valid @RequestBody FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = modelMapper.map(fornecedorDto, Fornecedor.class);
        fornecedorService.salvar(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
