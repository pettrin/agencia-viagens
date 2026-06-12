package com.agencia.viagens.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String localizacao;
    private String descricao;
    private Double precoPacote;
    private Boolean disponibilidadeHotel;

    @ElementCollection
    private List<String> atividades = new ArrayList<>();

    private Double mediaAvaliacao = 0.0;
    private Integer quantidadeAvaliacoes = 0;

    public Destino() {
    }

    public Destino(Long id, String nome, String localizacao, String descricao, Double precoPacote,
                   Boolean disponibilidadeHotel, List<String> atividades, Double mediaAvaliacao,
                   Integer quantidadeAvaliacoes) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.precoPacote = precoPacote;
        this.disponibilidadeHotel = disponibilidadeHotel;
        this.atividades = atividades;
        this.mediaAvaliacao = mediaAvaliacao;
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPrecoPacote() {
        return precoPacote;
    }

    public Boolean getDisponibilidadeHotel() {
        return disponibilidadeHotel;
    }

    public List<String> getAtividades() {
        return atividades;
    }

    public Double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public Integer getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecoPacote(Double precoPacote) {
        this.precoPacote = precoPacote;
    }

    public void setDisponibilidadeHotel(Boolean disponibilidadeHotel) {
        this.disponibilidadeHotel = disponibilidadeHotel;
    }

    public void setAtividades(List<String> atividades) {
        this.atividades = atividades;
    }

    public void setMediaAvaliacao(Double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public void setQuantidadeAvaliacoes(Integer quantidadeAvaliacoes) {
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
    }
}