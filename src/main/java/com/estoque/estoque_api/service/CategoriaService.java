package com.estoque.estoque_api.service;

import com.estoque.estoque_api.config.exception.BusinessException;
import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.repository.CategoriaRepository;
import com.estoque.estoque_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    public void Salvar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void ExcluirCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new BusinessException("Categoria não localizada."));
        if(produtoRepository.existsByCategoriaId(id)) {
            throw new BusinessException(
                    "Não é possível deletar: existem produtos vinculados a esta categoria."
            );
        }
        categoriaRepository.delete(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new BusinessException("Categoria não localizada."));
    }

    public void AtualizaCategoria (Long id, Categoria categoriaEnviada) {
        Categoria categoria = buscarPorId(id);
        categoria.setNome(categoriaEnviada.getNome());
        categoriaRepository.save(categoria);
    }
}
