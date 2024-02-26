package br.com.alura.codechella.domain.evento.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DadosCadastroEvento(
        @NotBlank(message = "Nome do evento é obrigatório!")
        String nome,
        @NotBlank(message = "Descrição do evento é obrigatória!")
        String descricao,
        @NotNull(message = "Categoria do evento é obrigatória!")
        CategoriaEvento categoria,
        @NotNull(message = "Data do evento é obrigatória!")
        @Future(message = "Data do evento deve ser uma data futura!")
        LocalDateTime data,
        @NotNull(message = "Endereço do evento é obrigatório!")
        @Valid
        Endereco endereco,
        @NotNull(message = "Pelo menos um tipo de ingresso deve ser informado!")
        @Valid
        List<DadosCadastroIngresso> ingressos
) {}
