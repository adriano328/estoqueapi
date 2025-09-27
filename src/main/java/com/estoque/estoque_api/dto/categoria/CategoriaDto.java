package com.estoque.estoque_api.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaDto {

    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;
}
