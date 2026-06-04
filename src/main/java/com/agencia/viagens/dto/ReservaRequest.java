package com.agencia.viagens.dto;

public class ReservaRequest {

    private String nomeCliente;
    private Integer quantidadePessoas;

    public ReservaRequest() {
    }

    public ReservaRequest(String nomeCliente, Integer quantidadePessoas) {
        this.nomeCliente = nomeCliente;
        this.quantidadePessoas = quantidadePessoas;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }
}