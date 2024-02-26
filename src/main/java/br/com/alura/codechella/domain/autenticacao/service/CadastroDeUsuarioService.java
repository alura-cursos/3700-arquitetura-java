package br.com.alura.codechella.domain.autenticacao.service;

import br.com.alura.codechella.domain.RegraDeNegocioException;
import br.com.alura.codechella.domain.autenticacao.entity.PerfilAcesso;
import br.com.alura.codechella.domain.autenticacao.entity.Usuario;
import br.com.alura.codechella.domain.autenticacao.repository.PerfilAcessoRepository;
import br.com.alura.codechella.domain.autenticacao.repository.UsuarioRepository;
import br.com.alura.codechella.domain.autenticacao.vo.DadosCadastroUsuario;
import br.com.alura.codechella.domain.autenticacao.vo.DadosUsuario;
import br.com.alura.codechella.domain.email.EnviadorDeEmail;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CadastroDeUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilAcessoRepository perfilAcessoRepository;
    private final PasswordEncoder passwordEncoder;
    private final EnviadorDeEmail enviadorDeEmail;

    public CadastroDeUsuarioService(UsuarioRepository usuarioRepository, PerfilAcessoRepository perfilAcessoRepository, PasswordEncoder passwordEncoder, EnviadorDeEmail enviadorDeEmail) {
        this.usuarioRepository = usuarioRepository;
        this.perfilAcessoRepository = perfilAcessoRepository;
        this.passwordEncoder = passwordEncoder;
        this.enviadorDeEmail = enviadorDeEmail;
    }

    public DadosUsuario cadastrarUsuario(DadosCadastroUsuario dados) {
        if (usuarioRepository.existsByEmailOrCpf(dados.email(), dados.cpf())) {
            throw new RegraDeNegocioException("Email e/ou CPF já cadastrado!");
        }

        var senhaAleatoria = gerarSenhaNumericaAleatoria();
        var hashSenha = passwordEncoder.encode(senhaAleatoria);
        var perfilComprador = carregarPerfilComprador();
        var usuario = new Usuario(dados, hashSenha, Set.of(perfilComprador));

        usuarioRepository.save(usuario);
        enviarEmail(usuario, senhaAleatoria);

        return new DadosUsuario(usuario);
    }

    private String gerarSenhaNumericaAleatoria() {
        return new Random()
                .ints(0, 10)
                .limit(6)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
    }

    private PerfilAcesso carregarPerfilComprador() {
        return perfilAcessoRepository.findByNome(PerfilAcesso.COMPRADOR);
    }

    private void enviarEmail(Usuario usuario, String senha) {
        var destinatario = usuario.getEmail();
        var assunto = "Boas vindas ao CodeChella!";
        var mensagem = "Seu cadastrado foi realizado! Acesse o site com seu email e esta senha: " +senha;
        enviadorDeEmail.enviar(destinatario, assunto, mensagem);
    }

}
