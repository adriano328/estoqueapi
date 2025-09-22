package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity @Table(name = "fornecedor")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razaosocial")
    private String razaoSocial;

    @Column(name = "nomefantasia")
    private String nomeFantasia;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "datacadastral")
    private Date dataCadastral;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;
}
