package br.com.alura.codechella.domain.evento.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroIngresso(
        @NotBlank(message = "Descrição do ingresso é obrigatória!")
        String descricao,
        @NotNull(message = "Preço do ingresso é obrigatório!")
        BigDecimal preco,
        @NotNull(message = "Quantidade do ingresso é obrigatória!")
        @Min(value = 1, message = "Quantidade mínima do ingresso deve ser 1!")
        Integer quantidade
) {}
