package br.com.alura.codechella.domain.autenticacao.repository;

import br.com.alura.codechella.domain.autenticacao.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.perfis WHERE u.email = :email")
    Usuario findByEmail(String email);

    boolean existsByEmailOrCpf(String email, String cpf);

}
