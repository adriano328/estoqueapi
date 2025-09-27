package com.estoque.estoque_api.dto.fornecedor;

import com.estoque.estoque_api.dto.endereco.EnderecoDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FornecedorAtualizaDto {
    private Long id;
    @NotBlank(message = "Razão social é obrigatório")
    private String razaoSocial;
    private String nomeFantasia;
    @NotBlank(message = "Situação é obrigatório")
    private String situacao;
    private LocalDate dataCadastral;
    private String email;
    private String telefone;
    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;
    private EnderecoDto endereco;
}
