package com.allexolic.controlefinanceiro.controllers;

import com.allexolic.controlefinanceiro.entities.Estabelecimento;
import com.allexolic.controlefinanceiro.services.EstabelecimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/estabelecimento")
public class EstabelecimentoController {
    private EstabelecimentoService estabelecimentoService;
    public EstabelecimentoController(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    @GetMapping
    public ResponseEntity<List<Estabelecimento>> getAllEstabelecimentos() {
        List<Estabelecimento> body = estabelecimentoService.getAllEstabelecimento();
        return ResponseEntity.ok().body(body);
    }
}
