package br.com.alura.codechella.domain.evento.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record DadosRealizarCompra(
        @NotNull(message = "Id do evento é obrigatório!")
        Long idEvento,
        @NotNull(message = "Forma de pagamento é obrigatória!")
        FormaDePagamento formaDePagamento,
        @NotNull(message = "Ao menos 1 ingresso deve ser comprado!")
        @Size(message = "Ao menos 1 ingresso deve ser comprado!")
        List<DadosIngressoRealizarCompra> ingressos) {
}
