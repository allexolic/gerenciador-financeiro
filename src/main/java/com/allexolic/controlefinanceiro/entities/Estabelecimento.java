package com.allexolic.controlefinanceiro.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "estabelecimento")
@Getter
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String numeroCnpj;
    private String endereco;
}
