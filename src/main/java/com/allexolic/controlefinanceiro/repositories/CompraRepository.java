package com.allexolic.controlefinanceiro.repositories;

import com.allexolic.controlefinanceiro.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, String> {
}
