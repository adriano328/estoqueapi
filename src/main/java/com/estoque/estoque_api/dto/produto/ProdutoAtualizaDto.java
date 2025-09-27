package com.estoque.estoque_api.dto.produto;

import com.estoque.estoque_api.model.Enum.UnidadeMedida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoAtualizaDto {

    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "O SKU é obrigatório")
    private String sku;

    private UnidadeMedida unidadeMedida;

    @NotNull(message = "A categoria é obrigatória")
    private Long categoriaId;
}
