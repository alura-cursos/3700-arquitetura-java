package br.com.alura.codechella.domain.evento.entity;

import br.com.alura.codechella.domain.autenticacao.entity.Usuario;
import br.com.alura.codechella.domain.evento.vo.FormaDePagamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    public Compra() {}

    public Compra(Usuario usuario, FormaDePagamento formaDePagamento) {
        this.data = LocalDateTime.now();
        this.usuario = usuario;
        this.formaDePagamento = formaDePagamento;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

}
