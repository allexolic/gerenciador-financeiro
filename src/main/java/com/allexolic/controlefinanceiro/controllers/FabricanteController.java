package com.allexolic.controlefinanceiro.controllers;

import com.allexolic.controlefinanceiro.entities.Fabricante;
import com.allexolic.controlefinanceiro.services.FabricanteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/fabricante")
public class FabricanteController {

    private FabricanteService service;
    public FabricanteController(FabricanteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Fabricante>> getFabricantes() {
        List<Fabricante> body = service.getAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> getFabricanteById(@PathVariable("id") String id) {
        Fabricante body = service.getById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Fabricante> saveFabricante(@RequestBody Fabricante fabricante,
                                                     UriComponentsBuilder builder, HttpServletRequest request) {
        Fabricante newFabricante = service.save(fabricante);
        URI location = builder.replacePath(request.getServletPath() + "/{id}").buildAndExpand(newFabricante.getId()).toUri();
        return ResponseEntity.created(location).body(newFabricante);
    }
}
