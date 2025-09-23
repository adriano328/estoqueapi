package com.estoque.estoque_api.dto.cep;

import lombok.Data;

@Data
public class CepDto {
    String cep;
    String logradouro;
    String complemento;
    String bairro;
    String localidade;
    String uf;
    String ibge;
    String gia;
    String ddd;
    String siafi;
    Boolean erro;
}
