package br.com.alura.codechella.controller;

import br.com.alura.codechella.domain.autenticacao.entity.Usuario;
import br.com.alura.codechella.domain.evento.service.CompraService;
import br.com.alura.codechella.domain.evento.vo.DadosCompra;
import br.com.alura.codechella.domain.evento.vo.DadosRealizarCompra;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("compras")
public class CompraController {

    private final CompraService service;

    public CompraController(CompraService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DadosCompra>> listarMinhasCompras(@AuthenticationPrincipal Usuario logado) {
        var compras = service.listarMinhasCompras(logado);
        return ResponseEntity.ok(compras);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosCompra> detalhar(@PathVariable Long id) {
        var compra = service.detalhar(id);
        return ResponseEntity.ok(compra);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCompra> realizarCompra(@RequestBody @Valid DadosRealizarCompra dadosRealizarCompra, @AuthenticationPrincipal Usuario logado, UriComponentsBuilder uriBuilder) {
        var dadosCompra = service.realizarCompra(dadosRealizarCompra, logado);
        var uri = uriBuilder.path("compras/{id}").buildAndExpand(dadosCompra.id()).toUri();
        return ResponseEntity.created(uri).body(dadosCompra);
    }

}
