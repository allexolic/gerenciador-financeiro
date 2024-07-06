package com.allexolic.controlefinanceiro.services;

import com.allexolic.controlefinanceiro.entities.Compra;
import com.allexolic.controlefinanceiro.repositories.CompraRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private CompraRepository compraRepository;
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<Compra> getAllCompra() {
        return compraRepository.findAll();
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
