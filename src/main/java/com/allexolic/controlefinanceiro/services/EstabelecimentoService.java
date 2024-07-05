package com.allexolic.controlefinanceiro.services;

import com.allexolic.controlefinanceiro.controllers.exceptions.ResourceNotFoundException;
import com.allexolic.controlefinanceiro.entities.Estabelecimento;
import com.allexolic.controlefinanceiro.repositories.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    private EstabelecimentoRepository estabelecimentoRepository;
    public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    public List<Estabelecimento> getAllEstabelecimento() {
        return estabelecimentoRepository.findAll();
    }

    public Estabelecimento getEstabelecimento(Long id) {
        Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
        if(!estabelecimento.isPresent())
            throw new ResourceNotFoundException("Estabelecimento não encontrado");
        return estabelecimento.get();
    }

    public void saveEstabelecimento(Estabelecimento estabelecimento) {
        estabelecimentoRepository.save(estabelecimento);
    }
}
