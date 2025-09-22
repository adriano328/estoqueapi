package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public void Salvar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
}
