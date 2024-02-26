package br.com.alura.codechella.domain.evento.repository;

import br.com.alura.codechella.domain.evento.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findAllByDataAfter(LocalDateTime data);

    Boolean existsByNomeIgnoringCase(String nome);

}
