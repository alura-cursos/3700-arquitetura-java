package br.com.alura.codechella.domain.evento.vo;

import br.com.alura.codechella.domain.evento.entity.Evento;

import java.time.LocalDateTime;

public record DadosEvento(Long id, String nome, String descricao, LocalDateTime data, CategoriaEvento categoria, Endereco endereco) {

    public DadosEvento(Evento evento) {
        this(evento.getId(), evento.getNome(), evento.getDescricao(), evento.getData(), evento.getCategoria(), evento.getEndereco());
    }

}
