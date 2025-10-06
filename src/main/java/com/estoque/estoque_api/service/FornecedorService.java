package com.estoque.estoque_api.service;

import com.estoque.estoque_api.config.exception.BusinessException;
import com.estoque.estoque_api.dto.endereco.EnderecoDto;
import com.estoque.estoque_api.dto.fornecedor.FornecedorAtualizaDto;
import com.estoque.estoque_api.model.Endereco;
import com.estoque.estoque_api.model.Fornecedor;
import com.estoque.estoque_api.repository.FornecedorRepository;
import com.estoque.estoque_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;
    public void salvar(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    public Fornecedor buscarPorCnpj(String cnpj) {
        return fornecedorRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new BusinessException(
                        "Fornecedor não encontrado para o CNPJ: " + cnpj
                ));
    }

    public Fornecedor buscarFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Fornecedor não localizado."));
    }

    public void ExcluiFornecedor(Long id) {
        Fornecedor fornecedor = buscarFornecedorPorId(id);
        if(produtoRepository.existsByFornecedor_Id(id)) {
            throw new BusinessException(
                    "Não é possível deletar: existem produtos vinculados a este fornecedor."
            );
        }
        fornecedorRepository.delete(fornecedor);
    }

    public Fornecedor atualizaFornecedor(Long id, Fornecedor fornecedorEnviado) {
        return fornecedorRepository.save(fornecedorEnviado);
    }

}
