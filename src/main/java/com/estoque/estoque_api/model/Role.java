package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=40)
    private String name;
}
