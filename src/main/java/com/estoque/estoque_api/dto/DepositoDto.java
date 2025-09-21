package com.estoque.estoque_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepositoDto {

    @NotBlank(message = "O nome do depósito é obrigatório")
    private String nome;
}
