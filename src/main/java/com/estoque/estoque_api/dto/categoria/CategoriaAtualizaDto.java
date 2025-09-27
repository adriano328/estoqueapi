package com.estoque.estoque_api.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaAtualizaDto {

    @NotNull(message = "O id é obrigatório")
    private Long id;
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;
}

