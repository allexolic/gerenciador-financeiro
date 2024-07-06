package com.allexolic.controlefinanceiro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "compra")
@Getter
@Setter
public class Compra {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    private Estabelecimento estabelecimento;
    private Date dataCompra;
    private Double valorTotal;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ItemCompra> itemCompra;
}
