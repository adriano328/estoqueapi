package com.estoque.estoque_api.dto.cnpj;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.*;

public class CnpjResponse {
    public String cnpj;
    public String razao_social;
    public String nome_fantasia;
    public String situacao_cadastral;
    public String data_situacao_cadastral;
    public String matriz_filial;
    public String data_inicio_atividade;
    public String cnae_principal;
    public List<String> cnaes_secundarios;
    public String natureza_juridica;
    public String logradouro;
    public String numero;
    public String complemento;
    public String bairro;
    public String cep;
    public String uf;
    public String municipio;
    public String email;
    public List<Telefone> telefones;
    public String capital_social;
    public String porte_empresa;

    public static class Telefone {
        public String ddd;
        public String numero;
        public Boolean is_fax;
    }

    // Captura campos que não mapeamos explicitamente (ex.: QSA)
    public Map<String,Object> extra = new HashMap<>();
    @JsonAnySetter
    void any(String k, Object v) { extra.put(k, v); }
}

