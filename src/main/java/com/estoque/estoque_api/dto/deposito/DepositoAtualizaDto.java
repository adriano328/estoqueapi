package com.estoque.estoque_api.dto.deposito;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositoAtualizaDto {
    @NotNull(message = "O id é obrigatório")
    private Long id;
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;
}
