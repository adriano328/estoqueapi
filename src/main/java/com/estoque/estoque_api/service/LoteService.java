package com.estoque.estoque_api.service;

import com.estoque.estoque_api.config.exception.BusinessException;
import com.estoque.estoque_api.model.Deposito;
import com.estoque.estoque_api.model.Lote;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.DepositoRepository;
import com.estoque.estoque_api.repository.LoteRepository;
import com.estoque.estoque_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoteService {

    private final LoteRepository loteRepository;
    private final ProdutoRepository produtoRepository;
    private final DepositoRepository depositoRepository;
    public void salvar(Lote lote) {
        loteRepository.save(lote);
    }

    public Lote BuscarPorLote(Long id) {
        return loteRepository.findById(id).orElseThrow(() -> new BusinessException("Lote não localizado."));
    }
    public void deletaLote(Long id) {
        Lote lote = BuscarPorLote(id);
        if(produtoRepository.existsByLotes_Id(id)) {
            throw new BusinessException(
                    "Não é possível deletar: existem produtos vinculados a este lote."
            );
        }
        loteRepository.delete(lote);
    }

    public void AtualizarLote(Long id, Lote loteEnviado) {
        Lote lote = loteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Lote não localizado."));
        Deposito deposito = depositoRepository.findById(loteEnviado.getDeposito().getId())
                .orElseThrow(() -> new BusinessException("Deposito não localizado."));
        Produto produto = produtoRepository.findById(loteEnviado.getProduto().getId())
                .orElseThrow(() -> new BusinessException("Produto não localizado."));

        lote.setId(id); lote.setNomeLote(loteEnviado.getNomeLote());
        lote.setDeposito(deposito); lote.setProduto(produto);
        lote.setQuantidade(loteEnviado.getQuantidade()); lote.setDeposito(loteEnviado.getDeposito());
        lote.setCustoUnitario(loteEnviado.getCustoUnitario()); lote.setDataFabricacao(loteEnviado.getDataFabricacao());
        lote.setDataVencimento(loteEnviado.getDataVencimento());
        loteRepository.save(lote);
    }
}
