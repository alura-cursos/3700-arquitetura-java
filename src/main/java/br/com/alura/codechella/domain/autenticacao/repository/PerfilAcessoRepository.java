package br.com.alura.codechella.domain.autenticacao.repository;

import br.com.alura.codechella.domain.autenticacao.entity.PerfilAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long> {

    PerfilAcesso findByNome(String nome);

}
