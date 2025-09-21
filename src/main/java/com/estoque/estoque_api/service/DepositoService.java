package com.estoque.estoque_api.service;

import com.estoque.estoque_api.config.exception.BusinessException;
import com.estoque.estoque_api.model.Deposito;
import com.estoque.estoque_api.repository.DepositoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositoService {

    private final DepositoRepository depositoRepository;

    public void salvar(Deposito deposito) {
        depositoRepository.save(deposito);
    }

}
