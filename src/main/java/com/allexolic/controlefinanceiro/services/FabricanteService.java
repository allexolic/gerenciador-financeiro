package com.allexolic.controlefinanceiro.services;

import com.allexolic.controlefinanceiro.controllers.exceptions.ResourceNotFoundException;
import com.allexolic.controlefinanceiro.entities.Fabricante;
import com.allexolic.controlefinanceiro.repositories.FabricanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    private FabricanteRepository repository;
    public FabricanteService(FabricanteRepository repository) {
        this.repository = repository;
    }

    public List<Fabricante> getAll(Pageable pageable){
        return repository.findAll(pageable).stream().toList();
    }

    public Fabricante getById(String id) {
        Optional<Fabricante> fabricante = repository.findById(id);
        if(!fabricante.isPresent())
            throw new ResourceNotFoundException("Fabricante não encontrado");
        return fabricante.get();
    }

    @Transactional
    public Fabricante save(Fabricante fabricante){
        return repository.save(fabricante);
    }
}
