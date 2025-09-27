package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.produto.ProdutoAtualizaDto;
import com.estoque.estoque_api.dto.produto.ProdutoDto;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Cadastra novo produto", description = "Cadastra novo produto")
    @PostMapping
    ResponseEntity<Produto> salvarProduto(@Valid @RequestBody ProdutoDto produtoDto) {
        Produto salvo = produtoService.salvar(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(summary = "Busca por produto", description = "Busca por produto")
    @GetMapping("busca-por-produto")
    ResponseEntity<ProdutoDto> buscarProdutoPorId(@RequestParam Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        ProdutoDto produtoDto = modelMapper.map(produto, ProdutoDto.class);
        return ResponseEntity.ok(produtoDto);
    }

    @Operation(summary = "Exclui produto", description = "Exclui produto")
    @DeleteMapping("excluir")
    public void ExcluirProduto(@RequestParam Long id) {
        produtoService.ExcluirProduto(id);
    }

    @Operation(summary = "Atualiza produto", description = "Atualiza produto")
    @PutMapping
    ResponseEntity<ProdutoAtualizaDto> atualizaProduto(@Valid @RequestBody ProdutoAtualizaDto produtoAtualizaDto) {
        Produto produto = modelMapper.map(produtoAtualizaDto, Produto.class);
        produtoService.AtualizaProduto(produtoAtualizaDto.getId(), produto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
