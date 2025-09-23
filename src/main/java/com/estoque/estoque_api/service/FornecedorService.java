package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Fornecedor;
import com.estoque.estoque_api.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public void salvar(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }
}
