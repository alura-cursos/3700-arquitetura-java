package br.com.alura.codechella.domain.evento.repository;

import br.com.alura.codechella.domain.evento.entity.Compra;
import br.com.alura.codechella.domain.evento.entity.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    @Query("SELECT COUNT(i) >= :quantidade FROM Ingresso i WHERE i.evento.id = :idEvento AND i.descricao = :descricao")
    Boolean temIngressoDisponivel(Long idEvento, String descricao, Integer quantidade);

    @Query("SELECT i FROM Ingresso i WHERE i.evento.id = :idEvento AND i.descricao = :descricao AND i.compra IS NULL ORDER BY i.id LIMIT :quantidade")
    List<Ingresso> buscarIngressosDisponiveis(Long idEvento, String descricao, Integer quantidade);

    List<Ingresso> findAllByCompra(Compra compra);

}
