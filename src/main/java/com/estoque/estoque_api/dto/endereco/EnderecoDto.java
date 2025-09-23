package com.estoque.estoque_api.dto.endereco;

import lombok.Data;

@Data
public class EnderecoDto {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String uf;
    private String municio;
}
