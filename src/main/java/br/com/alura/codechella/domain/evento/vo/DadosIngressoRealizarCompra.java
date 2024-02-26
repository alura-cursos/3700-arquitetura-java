package br.com.alura.codechella.domain.evento.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosIngressoRealizarCompra(
        @NotBlank(message = "Descrição do ingresso é obrigatória!")
        String descricao,
        @NotNull(message = "Quantidade do ingresso é obrigatória!")
        @Min(value = 1, message = "Quantidade mínima do ingresso deve ser 1!")
        Integer quantidade,
        @NotNull(message = "Tipo do ingresso é obrigatório!")
        TipoIngresso tipo) {
}
