package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.categoria.CategoriaAtualizaDto;
import com.estoque.estoque_api.dto.categoria.CategoriaDto;
import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cria nova categoria", description = "Cadastra uma nova categoria para produtos.")
    @PostMapping
    ResponseEntity<CategoriaDto> salvarCategoria(@Valid @RequestBody CategoriaDto categoriaDto) {
        com.estoque.estoque_api.model.Categoria categoria = modelMapper.map(categoriaDto, com.estoque.estoque_api.model.Categoria.class);
        categoriaService.Salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Exclui categoria", description = "Exclui categoria de produto.")
    @DeleteMapping("/excluir")
    public void deletaCategoria(@RequestParam Long id) {
        categoriaService.ExcluirCategoria(id);
    }

    @Operation(summary = "Busca categoria", description = "Busca categoria de produto.")
    @GetMapping("busca-categoria")
    ResponseEntity<CategoriaDto> buscaPorCategoria(@RequestParam Long id) {
        var categoria = categoriaService.buscarPorId(id);
        CategoriaDto categoriaDto = modelMapper.map(categoria, CategoriaDto.class);
        return ResponseEntity.ok(categoriaDto);
    }

    @Operation(summary = "Atualiza categoria", description = "Atualiza categoria de produto.")
    @PutMapping()
    ResponseEntity<CategoriaDto> AtualizaCategoria(@Valid @RequestBody CategoriaAtualizaDto categoriaDto) {
        Categoria categoria = modelMapper.map(categoriaDto, Categoria.class);
        categoriaService.AtualizaCategoria(categoriaDto.getId(),categoria);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
