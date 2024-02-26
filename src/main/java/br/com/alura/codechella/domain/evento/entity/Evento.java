package br.com.alura.codechella.domain.evento.entity;

import br.com.alura.codechella.domain.evento.vo.CategoriaEvento;
import br.com.alura.codechella.domain.evento.vo.DadosCadastroEvento;
import br.com.alura.codechella.domain.evento.vo.Endereco;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaEvento categoria;

    private LocalDateTime data;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ingresso> ingressos = new ArrayList<>();

    public Evento() {}

    public Evento(DadosCadastroEvento dados, List<Ingresso> ingressos) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.categoria = dados.categoria();
        this.data = dados.data();
        this.endereco = dados.endereco();
        this.ingressos.addAll(ingressos);
        this.ingressos.forEach(i -> i.setEvento(this));
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaEvento getCategoria() {
        return categoria;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Ingresso> getIngressos() {
        return Collections.unmodifiableList(ingressos);
    }

}
