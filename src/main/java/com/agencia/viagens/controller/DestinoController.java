package com.agencia.viagens.controller;

import com.agencia.viagens.dto.AvaliacaoRequest;
import com.agencia.viagens.dto.ReservaRequest;
import com.agencia.viagens.model.Destino;
import com.agencia.viagens.service.DestinoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @PostMapping
    public ResponseEntity<Destino> cadastrar(@RequestBody Destino destino) {
        Destino novoDestino = destinoService.cadastrar(destino);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDestino);
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(destinoService.listarTodos());
    }

    @GetMapping("/search")
    public ResponseEntity<?> pesquisar(@RequestParam String termo) {
        return ResponseEntity.ok(destinoService.pesquisar(termo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> buscarPorId(@PathVariable Long id) {
        Destino destino = destinoService.buscarPorId(id);
        return ResponseEntity.ok(destino);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizar(
            @PathVariable Long id,
            @RequestBody Destino destinoAtualizado) {

        Destino destino = destinoService.atualizar(id, destinoAtualizado);
        return ResponseEntity.ok(destino);
    }

    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<Destino> avaliar(
            @PathVariable Long id,
            @RequestBody AvaliacaoRequest avaliacaoRequest) {

        Destino destino = destinoService.avaliar(id, avaliacaoRequest.getNota());
        return ResponseEntity.ok(destino);
    }

    @PostMapping("/{id}/reservas")
    public ResponseEntity<String> reservar(
            @PathVariable Long id,
            @RequestBody ReservaRequest reservaRequest) {

        String mensagem = destinoService.reservar(id, reservaRequest);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        destinoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}