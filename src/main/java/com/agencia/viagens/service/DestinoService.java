package com.agencia.viagens.service;

import com.agencia.viagens.dto.ReservaRequest;
import com.agencia.viagens.exception.DestinoNaoEncontradoException;
import com.agencia.viagens.model.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinoService {

    private final List<Destino> destinos = new ArrayList<>();
    private Long proximoId = 1L;

    public Destino cadastrar(Destino destino) {
        destino.setId(proximoId++);
        destino.setMediaAvaliacao(0.0);
        destino.setQuantidadeAvaliacoes(0);

        destinos.add(destino);

        return destino;
    }

    public List<Destino> listarTodos() {
        return destinos;
    }

    public List<Destino> pesquisar(String termo) {
        if (termo == null || termo.isBlank()) {
            return destinos;
        }

        String termoMinusculo = termo.toLowerCase();

        return destinos.stream()
                .filter(destino ->
                        destino.getNome().toLowerCase().contains(termoMinusculo)
                                || destino.getLocalizacao().toLowerCase().contains(termoMinusculo)
                )
                .toList();
    }

    public Destino buscarPorId(Long id) {
        return destinos.stream()
                .filter(destino -> destino.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DestinoNaoEncontradoException("Destino não encontrado com o id: " + id));
    }

    public Destino atualizar(Long id, Destino destinoAtualizado) {
        Destino destinoExistente = buscarPorId(id);

        destinoExistente.setNome(destinoAtualizado.getNome());
        destinoExistente.setLocalizacao(destinoAtualizado.getLocalizacao());
        destinoExistente.setDescricao(destinoAtualizado.getDescricao());
        destinoExistente.setPrecoPacote(destinoAtualizado.getPrecoPacote());
        destinoExistente.setDisponibilidadeHotel(destinoAtualizado.getDisponibilidadeHotel());
        destinoExistente.setAtividades(destinoAtualizado.getAtividades());

        return destinoExistente;
    }

    public Destino avaliar(Long id, Integer nota) {
        if (nota == null || nota < 1 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 10.");
        }

        Destino destino = buscarPorId(id);

        Double mediaAtual = destino.getMediaAvaliacao();
        Integer quantidadeAtual = destino.getQuantidadeAvaliacoes();

        Double novaMedia = ((mediaAtual * quantidadeAtual) + nota) / (quantidadeAtual + 1);

        destino.setMediaAvaliacao(novaMedia);
        destino.setQuantidadeAvaliacoes(quantidadeAtual + 1);

        return destino;
    }

    public String reservar(Long id, ReservaRequest reservaRequest) {
        Destino destino = buscarPorId(id);

        return "Reserva realizada com sucesso para " 
                + reservaRequest.getNomeCliente()
                + ". Destino: "
                + destino.getNome()
                + ". Quantidade de pessoas: "
                + reservaRequest.getQuantidadePessoas()
                + ".";
    }

    public void excluir(Long id) {
        Destino destino = buscarPorId(id);
        destinos.remove(destino);
    }
}