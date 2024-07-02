package com.allexolic.controlefinanceiro.repositories;

import com.allexolic.controlefinanceiro.entities.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, String> {
}
