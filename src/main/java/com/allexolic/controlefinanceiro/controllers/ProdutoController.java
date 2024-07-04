package com.allexolic.controlefinanceiro.controllers;

import com.allexolic.controlefinanceiro.entities.Produto;
import com.allexolic.controlefinanceiro.services.ProdutoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/produto")
public class ProdutoController {

    private ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> body = produtoService.getAll();
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoPorId(@PathVariable("id") Long id) {
        Produto body = produtoService.getProduto(id);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping
    public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto, UriComponentsBuilder builder,
                                                  HttpServletRequest request) {
        Produto newProduto = produtoService.save(produto);
        URI location = builder.replacePath(request.getServletPath() + "/{id}").buildAndExpand(newProduto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
