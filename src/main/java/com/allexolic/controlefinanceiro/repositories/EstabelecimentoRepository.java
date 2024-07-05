package com.allexolic.controlefinanceiro.repositories;

import com.allexolic.controlefinanceiro.entities.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
}
