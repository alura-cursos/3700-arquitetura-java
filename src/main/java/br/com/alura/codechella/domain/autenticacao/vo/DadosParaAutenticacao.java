package br.com.alura.codechella.domain.autenticacao.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosParaAutenticacao(
        @NotBlank(message = "Email é obrigatório para realizar autenticação!")
        @Email(message = "Formato de email inválido!")
        String email,
        @NotBlank(message = "Senha é obrigatória para realizar autenticação!")
        String senha) {}
