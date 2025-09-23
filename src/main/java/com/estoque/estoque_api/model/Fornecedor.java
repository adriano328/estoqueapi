package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @NotNull
    @PastOrPresent
    @Column(name = "datacadastral", nullable = false)
    private LocalDate dataCadastral;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", unique = true, nullable = false)
    private Endereco endereco;
}
