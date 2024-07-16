package com.allexolic.controlefinanceiro.controllers;

import com.allexolic.controlefinanceiro.entities.Compra;
import com.allexolic.controlefinanceiro.services.CompraService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/compra")
public class CompraController {

    private CompraService compraService;
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }
    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras(@PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        List<Compra> body = compraService.getAllCompra(pageable);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraPorId(@PathVariable("id")String id) {
        Compra body = compraService.getCompraById(id);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping
    public ResponseEntity saveCompra(@RequestBody Compra compra, UriComponentsBuilder builder, HttpServletRequest request) {
        compraService.addCompra(compra);
        URI location = builder.replacePath(request.getServletPath() + "/{id}").buildAndExpand(compra.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
