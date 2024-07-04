package com.allexolic.controlefinanceiro.repositories;

import com.allexolic.controlefinanceiro.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
