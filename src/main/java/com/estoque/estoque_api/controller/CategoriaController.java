package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.categoria.CategoriaDto;
import com.estoque.estoque_api.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("categoria")
@RestController
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ModelMapper modelMapper;

    @PostMapping
    ResponseEntity<CategoriaDto> salvarCategoria(@Valid @RequestBody CategoriaDto categoriaDto) {
        com.estoque.estoque_api.model.Categoria categoria = modelMapper.map(categoriaDto, com.estoque.estoque_api.model.Categoria.class);
        categoriaService.Salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
