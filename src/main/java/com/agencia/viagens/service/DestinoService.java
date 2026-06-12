package com.agencia.viagens.service;

import com.agencia.viagens.dto.ReservaRequest;
import com.agencia.viagens.exception.DestinoNaoEncontradoException;
import com.agencia.viagens.model.Destino;
import com.agencia.viagens.repository.DestinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinoService {

    private final DestinoRepository destinoRepository;

    public DestinoService(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    public Destino cadastrar(Destino destino) {
        destino.setId(null);

        if (destino.getMediaAvaliacao() == null) {
            destino.setMediaAvaliacao(0.0);
        }

        if (destino.getQuantidadeAvaliacoes() == null) {
            destino.setQuantidadeAvaliacoes(0);
        }

        return destinoRepository.save(destino);
    }

    public List<Destino> listarTodos() {
        return destinoRepository.findAll();
    }

    public List<Destino> pesquisar(String termo) {
        if (termo == null || termo.isBlank()) {
            return destinoRepository.findAll();
        }

        return destinoRepository.findByNomeContainingIgnoreCaseOrLocalizacaoContainingIgnoreCase(
                termo,
                termo
        );
    }

    public Destino buscarPorId(Long id) {
        return destinoRepository.findById(id)
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

        return destinoRepository.save(destinoExistente);
    }

    public Destino avaliar(Long id, Integer nota) {
        if (nota == null || nota < 1 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 10.");
        }

        Destino destino = buscarPorId(id);

        Double mediaAtual = destino.getMediaAvaliacao();
        Integer quantidadeAtual = destino.getQuantidadeAvaliacoes();

        if (mediaAtual == null) {
            mediaAtual = 0.0;
        }

        if (quantidadeAtual == null) {
            quantidadeAtual = 0;
        }

        Double novaMedia = ((mediaAtual * quantidadeAtual) + nota) / (quantidadeAtual + 1);

        destino.setMediaAvaliacao(novaMedia);
        destino.setQuantidadeAvaliacoes(quantidadeAtual + 1);

        return destinoRepository.save(destino);
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
        destinoRepository.delete(destino);
    }
}