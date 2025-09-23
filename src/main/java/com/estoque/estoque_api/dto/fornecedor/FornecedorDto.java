package com.estoque.estoque_api.dto.fornecedor;

import com.estoque.estoque_api.dto.endereco.EnderecoDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class FornecedorDto {

    @NotBlank(message = "Razão social é obrigatório")
    private String razaoSocial;
    private String nomeFantasia;
    @NotBlank(message = "Situação é obrigatório")
    private String situacao;
    private LocalDate dataCadastral;
    private String email;
    private String telefone;
    private EnderecoDto endereco;
}
