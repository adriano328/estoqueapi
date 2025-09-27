package com.estoque.estoque_api.service;

import com.estoque.estoque_api.config.exception.BusinessException;
import com.estoque.estoque_api.model.Deposito;
import com.estoque.estoque_api.repository.DepositoRepository;
import com.estoque.estoque_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositoService {

    private final DepositoRepository depositoRepository;
    private final ProdutoRepository produtoRepository;

    public void salvar(Deposito deposito) {
        depositoRepository.save(deposito);
    }

    public Deposito buscarDepositoPorId(Long id) {
        return depositoRepository.findById(id).orElseThrow(() -> new BusinessException("Deposito não localizado."));
    }

    public void DeletaDeposito(Long id) {
        Deposito deposito = depositoRepository.findById(id).orElseThrow(() -> new BusinessException("Deposito não localizado."));
        if(produtoRepository.existsByLotes_Deposito_Id(id)) {
            throw new BusinessException(
                    "Não é possível deletar: existem produtos vinculados a este deposito."
            );
        }

        depositoRepository.delete(deposito);
    }

    public void atualizarDeposito(Long id, Deposito depositoEnviado) {
        Deposito deposito = buscarDepositoPorId(id);
        deposito.setNome(depositoEnviado.getNome());
        depositoRepository.save(deposito);
    }

}
