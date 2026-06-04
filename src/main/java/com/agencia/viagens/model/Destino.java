package com.agencia.viagens.model;

import java.util.ArrayList;
import java.util.List;

public class Destino {

    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private Double precoPacote;
    private Boolean disponibilidadeHotel;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Double getPrecoPacote() {
        return precoPacote;
    }

    public void setPrecoPacote(Double precoPacote) {
        this.precoPacote = precoPacote;
    }

    public Boolean getDisponibilidadeHotel() {
        return disponibilidadeHotel;
    }

    public void setDisponibilidadeHotel(Boolean disponibilidadeHotel) {
        this.disponibilidadeHotel = disponibilidadeHotel;
    }

    public List<String> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<String> atividades) {
        this.atividades = atividades;
    }

    public Double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(Double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public Integer getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public void setQuantidadeAvaliacoes(Integer quantidadeAvaliacoes) {
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
    }
}