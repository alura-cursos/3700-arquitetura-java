package br.com.alura.codechella.domain.evento.entity;

import br.com.alura.codechella.domain.evento.vo.TipoIngresso;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ingressos")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Evento evento;

    private String descricao;

    private BigDecimal preco;

    private String codigo;

    @ManyToOne
    private Compra compra;

    @Enumerated(EnumType.STRING)
    private TipoIngresso tipo;

    @Version
    private Integer versao;

    public Ingresso() {}

    public Ingresso(String descricao, BigDecimal preco) {
        this.descricao = descricao;
        this.preco = preco;
        this.codigo = UUID.randomUUID().toString();
    }

    public void registrarCompra(Compra compra, TipoIngresso tipo) {
        this.compra = compra;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public Compra getCompra() {
        return compra;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

}
