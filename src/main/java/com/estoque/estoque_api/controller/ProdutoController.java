package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.produto.ProdutoDto;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.service.ProdutoService;
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
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ModelMapper modelMapper;

    @PostMapping
    ResponseEntity<Produto> salvarProduto(@Valid @RequestBody ProdutoDto produtoDto) {
        Produto salvo = produtoService.salvar(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
