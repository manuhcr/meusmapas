package com.manoela.meusmapas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PontoNotFoundException extends RuntimeException {
    public PontoNotFoundException(Long idPonto) {
        super("Ponto não encontrado com ID: " + idPonto);
    }

    public PontoNotFoundException(String nomeMapa) {
        super("Nenhum ponto encontrado para o mapa com nome: " + nomeMapa);
    }
}


