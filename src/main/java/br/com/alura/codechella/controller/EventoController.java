package br.com.alura.codechella.controller;

import br.com.alura.codechella.domain.evento.service.EventoService;
import br.com.alura.codechella.domain.evento.vo.DadosCadastroEvento;
import br.com.alura.codechella.domain.evento.vo.DadosEvento;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DadosEvento>> listarProximosEventos() {
        var proximosEventos = service.listarProximosEventos();
        return ResponseEntity.ok(proximosEventos);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosEvento> detalhar(@PathVariable Long id) {
        var evento = service.detalhar(id);
        return ResponseEntity.ok(evento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosEvento> cadastrar(@RequestBody @Valid DadosCadastroEvento dadosCadastro, UriComponentsBuilder uriBuilder) {
        var dadosEvento = service.cadastrar(dadosCadastro);
        var uri = uriBuilder.path("eventos/{id}").buildAndExpand(dadosEvento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosEvento);
    }

}
