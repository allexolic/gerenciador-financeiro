package com.allexolic.controlefinanceiro.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "fabricante")
@Getter
public class Fabricante {
    @Id
    @UuidGenerator
    private String id;
    private String nome;
}
