package com.agencia.viagens.exception;

public class DestinoNaoEncontradoException extends RuntimeException {

    public DestinoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}