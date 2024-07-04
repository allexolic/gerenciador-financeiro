package com.allexolic.controlefinanceiro.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "produto")
@Getter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int peso;
    private String medida;
    @ManyToOne
    private Fabricante fabricante;
}
