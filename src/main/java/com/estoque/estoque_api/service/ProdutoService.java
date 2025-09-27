package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.produto.ProdutoDto;
import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.CategoriaRepository;
import com.estoque.estoque_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public Produto salvar(ProdutoDto produtoDto) {
        Categoria categoria = categoriaRepository.findById(produtoDto.getCategoriaId())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Categoria não encontrada"));
       Produto produto = new Produto();
       produto.setDescricao(produtoDto.getDescricao());
       produto.setSku(produtoDto.getSku());
       produto.setUnidadeMedida(produtoDto.getUnidadeMedida());
       produto.setCategoria(categoria);
       return produtoRepository.save(produto);
    }
}
