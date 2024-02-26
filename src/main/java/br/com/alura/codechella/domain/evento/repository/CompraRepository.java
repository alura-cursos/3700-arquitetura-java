package br.com.alura.codechella.domain.evento.repository;

import br.com.alura.codechella.domain.autenticacao.entity.Usuario;
import br.com.alura.codechella.domain.evento.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findAllByUsuario(Usuario usuario);

}
