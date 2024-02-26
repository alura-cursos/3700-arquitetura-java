package br.com.alura.codechella.domain.autenticacao.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DadosCadastroUsuario(
        @NotBlank(message = "Nome do usuário é obrigatório!")
        String nome,
        @NotBlank(message = "CPF do usuário é obrigatório!")
        @CPF(message = "Formato de CPF inválido!")
        String cpf,
        @NotBlank(message = "Email do usuário é obrigatório!")
        @Email(message = "Formato de email inválido!")
        String email,
        @NotNull(message = "Data de nascimento do usuário é obrigatória!")
        LocalDate dataNascimento) {
}
