package br.com.alura.codechella.domain.autenticacao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
@Table(name = "perfis_acesso")
public class PerfilAcesso implements GrantedAuthority {

    public static final String COMPRADOR = "ROLE_COMPRADOR";
    public static final String ADMIN = "ROLE_ADMIN";

    @Id
    private Long id;
    private String nome;

    public PerfilAcesso() {}

    public PerfilAcesso(String nome) {
        this.nome = nome;
    }

    public boolean isAdmin() {
        return this.nome.equals(ADMIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerfilAcesso that = (PerfilAcesso) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }

}
