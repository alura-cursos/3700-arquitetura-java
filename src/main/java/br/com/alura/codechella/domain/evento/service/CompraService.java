package br.com.alura.codechella.domain.evento.service;

import br.com.alura.codechella.domain.RegraDeNegocioException;
import br.com.alura.codechella.domain.autenticacao.entity.Usuario;
import br.com.alura.codechella.domain.evento.entity.Compra;
import br.com.alura.codechella.domain.evento.entity.Ingresso;
import br.com.alura.codechella.domain.evento.repository.CompraRepository;
import br.com.alura.codechella.domain.evento.repository.IngressoRepository;
import br.com.alura.codechella.domain.evento.vo.DadosCompra;
import br.com.alura.codechella.domain.evento.vo.DadosIngressoRealizarCompra;
import br.com.alura.codechella.domain.evento.vo.DadosRealizarCompra;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final IngressoRepository ingressoRepository;

    public CompraService(CompraRepository compraRepository, IngressoRepository ingressoRepository) {
        this.compraRepository = compraRepository;
        this.ingressoRepository = ingressoRepository;
    }

    public List<DadosCompra> listarMinhasCompras(Usuario usuario) {
        var dadosCompras = new ArrayList<DadosCompra>();
        var compras = compraRepository.findAllByUsuario(usuario);
        compras.forEach(compra -> {
            var ingressos = ingressoRepository.findAllByCompra(compra);
            dadosCompras.add(new DadosCompra(compra, ingressos));
        });
        return dadosCompras;
    }

    public DadosCompra detalhar(Long id) {
        var compra = compraRepository.findById(id).get();
        var ingressos = ingressoRepository.findAllByCompra(compra);
        return new DadosCompra(compra, ingressos);
    }

    public DadosCompra realizarCompra(DadosRealizarCompra dadosCompra, Usuario usuario) {
        validarDisponibilidadeDeIngressosParaOEvento(dadosCompra);
        var compra = salvarcompra(dadosCompra, usuario);
        var ingressos = comprarIngressos(dadosCompra, compra);
        return new DadosCompra(compra, ingressos);
    }

    private void validarDisponibilidadeDeIngressosParaOEvento(DadosRealizarCompra dadosCompra) {
        dadosCompra.ingressos().stream().forEach(ingresso -> {
            var disponivel = ingressoRepository.temIngressoDisponivel(dadosCompra.idEvento(), ingresso.descricao(), ingresso.quantidade());
            if (!disponivel) {
                throw new RegraDeNegocioException("Quantidade indisponível para ingresso do tipo " +ingresso.descricao());
            }
        });
    }

    private Compra salvarcompra(DadosRealizarCompra dadosCompra, Usuario usuario) {
        var compra = new Compra(usuario, dadosCompra.formaDePagamento());
        this.compraRepository.save(compra);
        return compra;
    }

    private List<Ingresso> comprarIngressos(DadosRealizarCompra dadosCompra, Compra compra) {
        var ingressosComprados = new ArrayList<Ingresso>();
        var quantidadePorDescricaoETipo = dadosCompra.ingressos()
                .stream()
                .collect(Collectors.groupingBy(
                        DadosIngressoRealizarCompra::descricao,
                        Collectors.groupingBy(
                                DadosIngressoRealizarCompra::tipo,
                                Collectors.summingInt(DadosIngressoRealizarCompra::quantidade))));

        quantidadePorDescricaoETipo.forEach((descricao, quantidadePorTipo) -> {
            quantidadePorTipo.forEach((tipo, quantidade) -> {
                var ingressosDisponiveis = ingressoRepository.buscarIngressosDisponiveis(dadosCompra.idEvento(), descricao, quantidade);
                if (ingressosDisponiveis.size() != quantidade) {
                    throw new RegraDeNegocioException("Quantidade indisponível para ingresso do tipo " +descricao);
                }
                ingressosDisponiveis.forEach(i -> {
                    i.registrarCompra(compra, tipo);
                    ingressosComprados.add(i);
                });
            });
        });

        return ingressosComprados;
    }

}
