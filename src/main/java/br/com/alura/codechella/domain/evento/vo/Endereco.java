package br.com.alura.codechella.domain.evento.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Endereco(
        @NotBlank(message = "Cidade é obrigatória!")
        String cidade,
        @NotBlank(message = "UF é obrigatória!")
        String uf,
        @NotBlank(message = "Logradouro é obrigatório!")
        String logradouro,
        @NotBlank(message = "Bairro é obrigatório!")
        String bairro,
        @NotBlank(message = "CEP é obrigatório!")
        String cep,
        String numero,
        String complemento
) {}
