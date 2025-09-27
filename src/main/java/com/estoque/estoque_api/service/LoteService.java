package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Lote;
import com.estoque.estoque_api.repository.LoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoteService {

    private final LoteRepository loteRepository;

    public void salvar(Lote lote) {
        loteRepository.save(lote);
    }
}
