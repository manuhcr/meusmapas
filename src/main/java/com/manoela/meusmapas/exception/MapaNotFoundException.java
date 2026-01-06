package com.manoela.meusmapas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MapaNotFoundException extends RuntimeException {
    public MapaNotFoundException(Long idMapa) {
        super("Mapa não encontrado com ID: " + idMapa);
    }

    public MapaNotFoundException(String nomeMapa) {
        super("Mapa não encontrado com nome: " + nomeMapa);
    }
}
