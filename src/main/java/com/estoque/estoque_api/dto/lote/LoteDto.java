package com.estoque.estoque_api.dto.lote;

import com.estoque.estoque_api.dto.deposito.DepositoLoteDto;
import com.estoque.estoque_api.dto.produto.ProdutoLoteDto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoteDto {

    @NotBlank(message = "O nome do lote é obrigatório.")
    private String nomeLote;

    @NotNull(message = "A quantidade é obrigatório.")
    private Integer quantidade;

    @NotNull(message = "Custo unitário é obrigatório")
    @Positive(message = "Custo unitário deve ser maior que zero")
    private Double custoUnitario;

    @NotNull(message = "Data de fabricação é obrigatória")
    @PastOrPresent(message = "Data de fabricação não pode ser no futuro")
    private LocalDate dataFabricacao;

    @NotNull(message = "Data de validade é obrigatória")
    @Future(message = "Data de validade deve estar no futuro")
    private LocalDate dataVencimento;

    @NotNull(message = "É obrigatório informar o produto.")
    private ProdutoLoteDto produto;

    @NotNull(message = "É obrigatório informar o depósito.")
    private DepositoLoteDto deposito;
}
