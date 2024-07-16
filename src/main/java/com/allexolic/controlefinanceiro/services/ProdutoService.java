package com.allexolic.controlefinanceiro.services;

import com.allexolic.controlefinanceiro.controllers.exceptions.ResourceNotFoundException;
import com.allexolic.controlefinanceiro.entities.Fabricante;
import com.allexolic.controlefinanceiro.entities.Produto;
import com.allexolic.controlefinanceiro.repositories.FabricanteRepository;
import com.allexolic.controlefinanceiro.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private FabricanteRepository fabricanteRepository;
    public ProdutoService(ProdutoRepository produtoRepository, FabricanteRepository fabricanteRepository) {
        this.produtoRepository = produtoRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    public List<Produto> getAll(Pageable pageable) {
        return produtoRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    public Produto save(Produto produto) {
        Optional<Fabricante> fabricante = fabricanteRepository.findById(produto.getFabricante().getId());
        if(!fabricante.isPresent())
            throw new ResourceNotFoundException("Registro de fabricante não encontrado");
        return produtoRepository.save(produto);
    }

    public Produto getProduto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(!produto.isPresent())
            throw new ResourceNotFoundException("Registro de produto não encontrado");
        return produto.get();
    }
}
