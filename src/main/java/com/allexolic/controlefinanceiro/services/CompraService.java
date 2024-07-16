package com.allexolic.controlefinanceiro.services;

import com.allexolic.controlefinanceiro.controllers.exceptions.ResourceNotFoundException;
import com.allexolic.controlefinanceiro.entities.Compra;
import com.allexolic.controlefinanceiro.repositories.CompraRepository;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private CompraRepository compraRepository;
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<Compra> getAllCompra(Pageable pageable) {
        return compraRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    public Compra getCompraById(String id) {
        Optional<Compra> compra = compraRepository.findById(id);
        if(!compra.isPresent())
            throw new ResourceNotFoundException("Registro de compra não encontrado");
        return compra.get();
    }

    public void addCompra(Compra compra) {
        Compra newCompra = new Compra();
        newCompra.setEstabelecimento(compra.getEstabelecimento());
        newCompra.setDataCompra(compra.getDataCompra());
        if( compra.getItemCompra() != null ) {
            newCompra.setItemCompra(compra.getItemCompra());
        }
        adicionarValorTotalCompra(newCompra);
        compraRepository.save(newCompra);
    }

    private void adicionarValorTotalCompra(Compra newCompra) {
        Double valorItem = 0D;
        for(val item : newCompra.getItemCompra())
            valorItem += item.getValor() * item.getQuantidade();
        newCompra.setValorTotal(valorItem);
    }
}
